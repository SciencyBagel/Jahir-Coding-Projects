#include "scanner.h"

using namespace std;

/*Function Definitions*/

stringstream read_file(string file_name)
{
	ifstream FILE;
	FILE.open(file_name);

	if (!FILE.is_open())
	{
		cout << "File '" << file_name << "' failed to open" << endl;

		cout << endl;
		cout << "Press 'Enter' to close this window..." << endl;
		getchar();

		exit(0);
	}
	else if (FILE.eof())
	{
		exit(0);
	}

	stringstream file_stream;
	string file_buffer;

	while (!FILE.eof())
	{
		getline(FILE, file_buffer);

		if (FILE.eof())
		{
			continue;
		}

		file_stream << file_buffer << '\n';
	}

	FILE.close();

	return file_stream;
}

void getFileStream(stringstream& input_stream)
{
	stringstream aux_stream;
	string user_input;
	string aux_string;//auxillary string for stringstream

	getline(cin, user_input);
	aux_stream.str(user_input);
	getline(aux_stream, aux_string, ' ');

	//make sure user is using commandline invocation
	if (aux_string != "scanner")
	{
		exit(0);
	}

	//file handling
	getline(aux_stream, aux_string, '\n'); //expected file name
	string file_name = aux_string;

	input_stream = read_file(file_name);
}

void t_error()
{
	cout << "error." << endl;

	cout << endl;
	cout << "Press 'Enter' to close this window..." << endl;
	getchar();

	exit(1);
}

/*DFA Definitions*/

int DFA::get_index(char c)
{
	if (int(c) == SPACE || int(c) == TAB)
	{
		return 0;
	}
	else if (int(c) == NL)
	{
		return 1;
	}
	else if (c == '/')
	{
		return 2;
	}
	else if (c == '*')
	{
		return 3;
	}
	else if (c == '(')
	{
		return 4;
	}
	else if (c == ')')
	{
		return 5;
	}
	else if (c == '+')
	{
		return 6;
	}
	else if (c == '-')
	{
		return 7;
	}
	else if (c == ':')
	{
		return 8;
	}
	else if (c == '=')
	{
		return 9;
	}
	else if (c == '.')
	{
		return 10;
	}
	else if (isdigit(c))
	{
		return 11;
	}
	else if (isalpha(c))
	{
		return 12;
	}
	else
	{
		return 13;
	}

}


void DFA::scan(stringstream& file_stream)
{
	cur_state = 1;

	char c;
	string tok_builder;

	//loop until dead end or bad bit
	while (cur_state != -1 && file_stream.good() && cur_state!=-2)
	{

		file_stream.get(c);

		if (file_stream.eof())
		{
			return;
		}

		//use tok_builder for nonwhitespace
		if (int(c) != NL && int(c) != SPACE && int(c) != TAB)
		{
			tok_builder.push_back(c);
		}

		prev_state = cur_state; //remember previous state
		cur_state = transition_table[cur_state-1][get_index(c)];

		//whitespace logic

		if (cur_state == 17) //state 17 -> whitespace
		{
			c = file_stream.peek();

			if (file_stream.eof())
			{
				return;
			}

			cur_state = transition_table[cur_state - 1][get_index(c)];

			if (cur_state == -1) //non whitespace char found, skip entire loop to exit back to main
			{
				file_stream.get(c); //this should be another white space character. The char after this character though should be nonwhitespace
				return;
			}
		}

	}

	//if bad bit, set current state to -1 to declare dead end for DFA
	if (!file_stream.good())
	{
		cur_state = -1;
	}


	if (tok_builder == "read" || tok_builder == "write")
	{
		token_list.push_back(tok_builder);
		return;
	}
	else if (token_table[prev_state - 1] == "white_space")
	{
		return; //we want to ignore white_space, so this should not be pushed into the token_list
	}
	else if (token_table[prev_state-1] == "comment")
	{
		return;
	}
	else if (cur_state == -2 || token_table[prev_state-1] == "error")//invalid token
	{
		token_list.push_back("error"); //return error flag to token_list for book keeping.
		t_error();
	}

	//TODO: In the case that cur_state is -1, we have to make sure that it is not an invalid token

	/*
	The previous else if statement only checked certain conditions. We now need to check if an unexpected character was read.
	For example: if token_table[prev_state-1] == lparen, it could be that the dead end was encountered because a character like '@' was read.
	*/



	token_list.push_back(token_table[prev_state-1]);
}

DFA::DFA()
{
	prev_state = 1;
	cur_state = 1; //initialize current state

	int i = 0;
	int j = 0;

	//initialize transition table to "-1" which means a dead end
	for (i = 0; i < 18; i++)
	{
		for (j = 0; j < COLS; j++)
		{
			transition_table[i][j] = -1;
		}
	}

	//Add the correct states for transition table (the end expression is the state to go to)
	// -2 means invalid state, output error

		/*STATE 1*/
		transition_table[0][0] = 17;
		transition_table[0][1] = 17;
		transition_table[0][2] = 2;
		transition_table[0][3] = 10;
		transition_table[0][4] = 6;
		transition_table[0][5] = 7;
		transition_table[0][6] = 8;
		transition_table[0][7] = 9;
		transition_table[0][8] = 11;
		transition_table[0][10] = 13;
		transition_table[0][11] = 14;
		transition_table[0][12] = 16;

		/*STATE 2*/
		transition_table[1][2] = 3;
		transition_table[1][3] = 4;

		for(j = 4; j< COLS; j++) //error transition states
		{
			transition_table[1][j] = -2;
		}

		/*STATE 3*/
		transition_table[2][0] = 3;
		transition_table[2][1] = 18;

		for (j = 2; j < COLS; j++)
		{
			transition_table[2][j] = 3;
		}

		/*STATE 4*/
		transition_table[3][0] = 4;
		transition_table[3][1] = 4;
		transition_table[3][2] = 4;
		transition_table[3][3] = 5;

		for (j = 4; j < COLS; j++)
		{
			transition_table[3][j] = 4;
		}

		/*STATE 5*/
		transition_table[4][0] = 4;
		transition_table[4][1] = 4;
		transition_table[4][2] = 18;
		transition_table[4][3]= 5;

		for (j = 4; j < COLS; j++)
		{
			transition_table[4][j] = 4;
		}

		/*STATE 6*/
		for(j = 2; j< COLS; j++) //error transition states
		{
			transition_table[5][j] = -2;
		}

		/*STATE 7*/
		for(j = 2; j< COLS; j++) //error transition states
		{
			transition_table[6][j] = -2;
		}

		/*STATE 8*/
		for(j = 2; j< COLS; j++) //error transition states
		{
			transition_table[7][j] = -2;
		}

		/*STATE 9*/
		for(j = 2; j< COLS; j++) //error transition states
		{
			transition_table[8][j] = -2;
		}

		/*STATE 10*/
		for(j = 2; j< COLS; j++) //error transition states
		{
			transition_table[9][j] = -2;
		}

		/*STATE 11*/
		transition_table[10][9] = 12;

			/*
				Error States are already managed because token_table holds error at the this index.
			*/

		/*STATE 12*/
		for(j = 2; j< COLS; j++) //error transition states
		{
			transition_table[11][j] = -2;
		}

		/*STATE 13*/
		transition_table[12][11] = 15;

			/*
				Error States are already managed because token_table holds error at the this index.
			*/

		/*STATE 14*/
		transition_table[13][10] = 15;
		transition_table[13][11] = 14;

		for(j = 2; j < 10; j++) //error transition states
		{
			transition_table[13][j] = -2;
		}
		transition_table[13][12] = -2;
		transition_table[13][13] = -2;

		/*STATE 15*/
		transition_table[14][11] = 15;

		for(j = 2; j < 11; j++) //error transition states
		{
			transition_table[14][j] = -2;
		}
		transition_table[14][12] = -2;
		transition_table[14][13] = -2;

		/*STATE 16*/
		transition_table[15][11] = 16;
		transition_table[15][12] = 16;

		for(j = 2; j < 11; j++) //error transition states
		{
			transition_table[15][j] = -2;
		}
		transition_table[15][13] = -2;

		/*STATE 17*/
		transition_table[16][13] = -2;
		transition_table[16][0] = 17;
		transition_table[16][1] = 17;

}

DFA::~DFA()
{
	if (!token_list.empty())
	{
		cout << endl;

		cout << "( ";

		for (int i = 0; i < token_list.size(); i++)
		{

			if (i == token_list.size() - 1)//last token
			{
				cout << token_list[i] << " )" << endl;
			}
			else
			{
				cout << token_list[i] << ", ";
			}

		}

	}

	cout << endl;
	cout << "Press 'Enter' to close this window..." << endl;
	getchar();
}
