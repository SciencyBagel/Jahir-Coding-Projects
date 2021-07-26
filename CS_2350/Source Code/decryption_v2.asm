;------------------
; Jahir F. Montes
; R11725642
; Assignment 5
;------------------

;-----------------------------------------------------------------------------
; DESCRIPTION:

; This program prompts for a message and a key. It takes the message and key
; and performs symmetric encryption. The result of the decipher, or cipher,
; will then be printed.

; If the program is ran again, user can type in ciphered message and input the
; same key which was used to get the ciphered message. The result of such an
; operation would decipher the previously ciphered message.
;-----------------------------------------------------------------------------


;-----------------------------------------------------------------------------
; METHODOLOGY

; The method used to cipher and decipher strings was the following: for a given
; inputMessage and inputKey, the program XORs a byte from the inputMessage with
; a byte of the inputKey. After performing the XOR operation on both of the
; initial bytes of inputKey and inputMessage, the program traverses the next
; bytes of both strings. If the inputKey has been exhausted (no more bytes to
; read), and there are still bytes to be ciphered/deciphered in inputMessage,
; the program will restore the initial offset of the inputKey to the beginning.
; This process would repeat until the end of the inputMessage has been reached.
;-----------------------------------------------------------------------------

INCLUDE Irvine32.inc

.data

; Prompts and Messages
msg1 BYTE "Enter Message:   ", 0
msg2 BYTE "Enter Key:   ", 0
msg3 BYTE "Result:  ", 0

; decoding data
MAX = 500 ; max chars for ReadString
MIN = 20
inputMessage BYTE 500 DUP(0)
inputKey BYTE 20 DUP(0)
result BYTE 500 DUP(0)

; student info
s_name BYTE "Jahir F. Montes", 0
Rnumber BYTE "R11725642", 0

.code
main PROC
  ;----------------------------------
  ; Code block for printing student information

  ; print student name
  mov edx, OFFSET s_name
  call WriteString
  call Crlf

  ; print R-number
  mov edx, OFFSET Rnumber
  call WriteString
  call Crlf

  call Crlf
  ;----------------------------------



  ;----------------------------------
  ; Code block to get inputMessage

  ; Print prompt
  mov edx, OFFSET msg1
  call WriteString

  ; Take input from keyboard
  mov edx, OFFSET inputMessage
  mov ecx, MAX
  call ReadString
  ;----------------------------------



  ;----------------------------------
  ; Code block to get inputKey

  ; Print prompt
  mov edx, OFFSET msg2
  call WriteString

  ; Take input from keyboard
  mov edx, OFFSET inputKey
  mov ecx, MIN
  call ReadString
  ;----------------------------------



  ;----------------------------------
  ; Code block for decoding and printing result

  ; Perform Decoding
  mov edx, OFFSET inputMessage
  mov ebx, OFFSET inputKey
  mov eax, OFFSET result
  call Decode

  ; Print Result
  mov edx, eax
  call WriteString
  ;----------------------------------

main ENDP
exit

; PROCEDURES

Decode PROC

  ; DESCRIPTION: encodes/decodes an message, and returns a key

  ; REQUIRES: edx, ebx, and eax to have appropriate offsets...edx and ebx need to be null terminated
  ; RECEIVES: edx as the inputMessage...ebx as the inputKey...eax as the destination address of the result
  ; RETURNS:  ciphered/deciphered null-terminated offset in eax

  push eax
  push ebx

  L1:
    ; XOR inputMessage and inputKey and store result at eax offset
    mov ch, [edx]
    xor ch, [ebx]
    mov [eax], ch

    ; increment indicies
    inc edx
    inc ebx
    inc eax

    ; check if next inputMessage byte is NULL
    mov ch, [edx]
    cmp ch, 0
    je ExitLoop ; if NULL, exit loop

    ; check inputKey byte is null
    mov ch, [ebx]
    cmp ch, 0
    jne L1 ; if next inputKey byte is non-null byte, restart loop

    ; inputKey is exhausted, reset inputKey for repetition
    pop ebx ; restore
    push ebx ; save for later

    ; restart loop with non-exhausted inputKey
    jmp L1 ;

  ExitLoop:

  ; add null-terminator to end of string
  mov bh, 0
  mov [eax], bh

  pop ebx ; restore ebx back to original offset
  pop eax ; restore eax to beginning of resulting string

  RET
Decode ENDP

END main
