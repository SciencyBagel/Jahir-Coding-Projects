/*PROGRAM DECLARATIONS*/

#ifndef _SCANNER_
#define _SCANNER_

#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <sstream>

#define NL 10
#define TAB 9
#define SPACE 32

#define ROWS 18
#define COLS 14

using namespace std;

/*FUNCTION DECLARATIONS*/
stringstream read_file(string file_name);

void getFileStream(stringstream& input_stream);

void t_error();

/*CLASS DECLARATIONS*/
class DFA
{
private:

	//DATA STRUCTURES
	vector<string> token_list;//to save scanned token types
	string token_table[ROWS] = {"error","div","error","error","error","lparen","rparen","plus","minus","times","error","assign","error","number","number","id","white_space","comment" };//table of token types
	char transition_table[ROWS][COLS];

	//PRIVATE FUNCTIONS
	int get_index(char c);//takes in char, returns column index of character read for transition table

	//VARS AND AUXILLARY
	int cur_state;
	int prev_state;
	string token;

public:

	/*CONSTRUCTOR: for initializing*/
	DFA();

	/*DESTRUCTOR: for printing*/
	~DFA(); 

	//PUBLIC FUNCTIONS
	void scan(stringstream& file_stream);//returns token type

};

#endif
