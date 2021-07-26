COMMENT #
Name: Jahir F. Montes
Date: 04/01/2021
Assignment: 3

//////////////
PROBLEM DESCRIPTION:
//////////////

In this program, I need to decode a string. This is done by subtracting 1 from the ASCII value 
of each character, and reversing the entire string after. Then, the decoded string should be stored at 
location "decodedString." Finally, it should be printed.

//////////////
PSUEDOCODE
//////////////

encoded: encoded string location
decoded: decoded string location
string_length: length of string
cur_char: current char

index := 0

Function main

DO
	cur_char := encoded[index] //this line is skipped in actual code...included for clarity
	DEC cur_char //subtract one from the character to commence decoding it
	PUSH cur_char //push to stack

	INC index

WHILE index < string_length

index := 0

DO
	POP decoded[index] //pop the stack and store the characters from the beginning of "decoded." This flips the string.

	INC index

WHILE index < string_length

PRINT decoded

EndFunction

#

INCLUDE Irvine32.inc

.data		; Data segment.  Feel free to add variables before or after this block, but don't add anything in between the following 4 memory operands.
	encodedString BYTE "/vpz!offc!fdop!ebi!idjix!gp!mmb!uvc!fwjmb!offc!sfwf!ebi!idjix!gp!fopo!-utve!djnpub!fojg!gp!eovpn!b!fdvepsq!emvpx!vpz!-fnju!b!ub!npub!fop!-tsf{ffxu!iujx!usbqb!gmftsvpz!ldjq!pu!fsfx!vpz!gj!ubiu!opjupo!hojutfssb!zmuihjmt!b!tj!uJ"
	nullTerm BYTE 0										; Null Terminator for encodedString.
	decodedString BYTE SIZEOF encodedString DUP('?')	; Storage location for the final decoded string. You MUST put the final result in this variable!
	nullTerm2 BYTE 0									; Null Terminator for decodedString

	; Place your additional memory operands below (if any)
	stringLength DWORD LENGTHOF decodedString
	myName BYTE "Jahir Montes",0dh,0ah,0
	rNumber BYTE "R11725642",0dh,0ah,0

.code
main PROC

	mov ecx, stringLength ;set loop 1 counter
	mov esi, OFFSET encodedString ;set esi to point at "encodedString"

	L1: ;read encodedString (stack push)
		dec BYTE PTR [esi] ; subtract 1 form the current char pointed by esi..."BYTE PTR" to confirm the size
		push [esi] ;push current character onto the stack

		inc esi ;move index to next char in encodedString
		loop L1

	mov ecx, stringLength ;set loop 2 counter
	mov esi, 0

	L2: ;write decodedString (stack pop)
		pop eax ;store decoded char in ax
		mov decodedString[esi], al

		inc esi
		loop L2

	;print myName
	mov edx, OFFSET myName ;meet writeString precondition
	call writestring ;prints screen pointed to by edx

	;print R#
	mov edx, OFFSET rNumber
	call writeString

	call crlf ;carriage return line feed

	;print decoded string
	mov edx, OFFSET decodedString
	call writeString
	call crlf

	exit
main ENDP
END main
