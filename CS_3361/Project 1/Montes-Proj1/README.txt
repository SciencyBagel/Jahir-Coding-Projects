README

Files Included: main.cpp README.txt scanner.cpp scanner.h Montes-Proj1.pdf

Description:

Scans a file and prints out the tokens recognized. If invalid token scanned, error will print out to screen.
To start the executable, do "start <executable_name>". Then, the program will expect the user to run "scanner <file_name>" in the commandline.
For the program to scan the file, it is REQUIRED that the input file be in the same directory as the executable file.

For example, if my input file is named "sample.txt", I should run the line "scanner sample.txt" during runtime.

To avoid the terminal closing too fast, there will be a prompt at the end of the program; the
program will finish if "Enter" is typed.

Compilation (Windows):

1. download all contents of folder to desktop
2. In the terminal, go to the desktop directory.
3. run "g++ -o <program_name> main.cpp scanner.cpp"

~~Note: This was debugged in Visual Studio.
