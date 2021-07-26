//Jahir Montes R11725642

#include "scanner.h"

using namespace std;

int main(void)
{
	//Store all file content into a stringstream
	stringstream file_stream;
	getFileStream(file_stream);

	//DFA logic
	DFA scanner;

	while (file_stream.good())
	{
		/*
			Each iteration of this loop is an attempt to recognize a token. E.g., the next iteration after the first one
			is another attempt to recognize a token.

			For each token type recognized, it will be stored at: scanner.token_list and printed for DFA's destructor.
		*/

		scanner.scan(file_stream);
	}

	return 0;
}
