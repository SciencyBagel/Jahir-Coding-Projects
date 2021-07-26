; Jahir F. Montes
; 4/19/2021
; Assignment #4

; Developing basic string procedures

;----------------------------------
; 			methodology for strcat
; In loop 1, copy byte from string1 to newString, until null terminator found
; In loop 2, continue where you left off from loop1 in newString and copy string2's bytes in the same fashion.
; Add null terminator

; 			methodology for strrep
; create space in memory called oldString to copy the string that needs to be repeated
; save eax value into ecx for the loop counter
; loop string pointed to by esi until 0 is found, each iteration is copying a byte from [esi] to oldString
; once oldString is saved, copy the string into newString offset specified amount of times.

; 			methodology for strlower
;	traverse [esi] and OR individual bits to set the 6th bit to convert to lower case
; loop until null terminator found

; 			methodology for strlower
;	check if current byte is a lowercase character
; if it is, AND bits at [esi] so that the 6th bit is cleared.
; else, copy the string directly into newString because no conversion needed.
;----------------------------------
INCLUDE Irvine32.inc


.data
	textString BYTE "You entered the string: "
	inputString BYTE 50 DUP(0)
	hardcodeString BYTE " for CS2350. ", 0
	newString BYTE 500 DUP(0)

	prompt BYTE "Enter a string to manipulate: ",0
	msg1 BYTE "Post Concatenation: ",0
	msg2 BYTE "Post Repetition:    ",0
	msg3 BYTE "Post Lowercase:     ",0
	msg4 BYTE "Post Uppercase:     ",0

	; Place your additional memory operands below (if any)

.code
main PROC																; DO NOT CHANGE THE CODE WITHIN THE MAIN PROCEDURE.
	; Prompt the user for string input (50 character maximum)
	mov edx, OFFSET prompt
	call WriteString

	; Read in the user's response
	mov edx, OFFSET inputString
	mov ecx, SIZEOF inputString
	call ReadString

	; Print the user's response to the screen.
	mov edx, OFFSET textString
	call WriteString
	call Crlf			; Print end line character.

	; -------------------------------------------------------------
	; Code block for calling strcat and printing results to screen.
	mov edi, OFFSET newString
	mov esi, OFFSET inputString
	mov edx, OFFSET hardcodeString
	call strcat
	mov edx, OFFSET msg1
	call WriteString	; Print msg1
	mov edx, edi		; Move the address in EDI to EDX
	call WriteString	; Print output of strcat
	call Crlf			; Print end line character.
	; -------------------------------------------------------------


	; -------------------------------------------------------------
	; Code block for calling strrep and printing results to screen.
	mov edi, OFFSET newString
	mov esi, OFFSET newString
	mov eax, 3
	call strrep
	mov edx, OFFSET msg2
	call WriteString	; Print msg2
	mov edx, edi		; Move the address in EDI to EDX
	call WriteString	; Print output of strcat
	call Crlf			; Print end line character.
	; -------------------------------------------------------------


	; -------------------------------------------------------------
	; Code block for calling strlower and printing results to screen.
	mov edi, OFFSET newString
	mov esi, OFFSET newString
	call strlower
	mov edx, OFFSET msg3
	call WriteString	; Print msg3
	mov edx, edi		; Move the address in EDI to EDX
	call WriteString	; Print output of strcat
	call Crlf			; Print end line character.
	; -------------------------------------------------------------


	; -------------------------------------------------------------
	; Code block for calling strupper and printing results to screen.
	mov edi, OFFSET newString
	mov esi, OFFSET newString
	call strupper
	mov edx, OFFSET msg4
	call WriteString	; Print msg4
	mov edx, edi		; Move the address in EDI to EDX
	call WriteString	; Print output of strcat
	call Crlf			; Print end line character.
	; -------------------------------------------------------------


	exit
main ENDP
; PROCEDURES

strcat PROC
.data
	student_name BYTE "Jahir F. Montes", 0
	Rnumber BYTE "R11725642", 0
.code
	;----------------------------------
	; Codeblock to print name, R number
	push edx

	; print name
	call Crlf
	mov edx, OFFSET student_name
	call WriteString
	call Crlf

	; print R number
	mov edx, OFFSET Rnumber
	call WriteString
	call Crlf
	call Crlf

	pop edx
	;----------------------------------

	push edi ; to restore newString offset after using indirect addressing

	;----------------------------------
	; Code block to copy first part of the string
	L1:
		; copy char from string1 into newString
		mov al, [esi]
		mov [edi], al

		; increment indicies
		inc edi
		inc esi

	; check if byte in string1 is a null terminator
	mov al, [esi]
	cmp al, 0
	jne L1 ; loop until esi points to null terminator
	;----------------------------------


	;----------------------------------
	;Code block to concatenate string2 into newString
	L2:
		mov al, [edx]
		mov [edi], al

		; increment indicies
		inc edi
		inc edx

	mov al, [edx]
	cmp al, 0
	jne L2

	; add null terminator to new string
	mov al, 0
	mov [edi], al
	;----------------------------------

	pop edi ; restore newString offset

	RET
strcat ENDP

strrep PROC
.data
	oldString BYTE 500 DUP(0) ; To store oldString. Used to take account of ESI and EDI pointing at the same place.
.code

	mov ecx, eax ; save int32 value into ecx loop counter in L1. Also frees up eax.
	push edi

	;----------------------------------
	; Code block to store string pointed to by esi into memory at location "oldString"
	mov eax, 0
	L0:
		mov bl, [esi]
		mov oldString[eax], bl

		inc eax
		inc esi

	mov bl, [esi]
	cmp bl, 0
	jne L0
	;----------------------------------



	;----------------------------------
	; Code block for copying string repeatedly
	mov eax, 0
	L1:
		; copy byte of oldString to newString
		mov bl, oldString[eax]
		mov [edi], bl

		; increment indicies and indirect operands
		inc eax
		inc edi

		; check for null terminator
		mov bl, oldString[eax]
		cmp bl, 0
		jne L1

		; restore oldString index to beginning
		mov eax, 0

	loop L1

	; add null terminator to newString
	mov bl, 0
	mov [edi], bl
	;----------------------------------

	pop edi

	RET
strrep ENDP

strlower PROC

	push edi

	;----------------------------------
	; Converting string to lowercase and storing it in newString
	L1:
		; to lowercase
		mov al, [esi]
		or al, 00100000b
		mov [edi], al

		; increment indicies
		inc esi
		inc edi

	mov al, [esi]
	cmp al, 0
	jne L1

	; add null terminator to newString
	mov al, 0
	mov [edi], al
	;----------------------------------
	pop edi

	RET
strlower ENDP

strupper PROC

	push edi

	;----------------------------------
	; Converting string to uppercase and storing it in newString
	L1:

		mov al, [esi]

		; Check if current byte is a lowercase character. If byte is in range [61h, 7Ah], it is lowercase and needs to be converted.
		cmp al, 61h
		jb L2

		cmp al, 7Ah
		ja L2

		; to uppercase
		and al, 11011111b
	L2:
		; copy byte to newString
		mov [edi], al

		; increment indicies
		inc esi
		inc edi

	mov al, [esi]
	cmp al, 0
	jne L1

	; add null terminator to newString
	mov al, 0
	mov [edi], al
	;----------------------------------
	pop edi

	RET
strupper ENDP

END main
