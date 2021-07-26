; Jahir F. Montes
; R11725642
; 05/01/2021
; Assignment #6

;--------------------------------------------------------------------
; Description and Methodology

; -Description-
  ; This program maps out the pattern of the roman numberals which is
  ; consistent for every decimal place.

; -Example of Pattern-
  ; The quantity "4" is represented as IV in roman numerals...
  ; The quantity "40" is represented as XL in roman numerals...

  ; The quantity "6" is represented as VI in roman numerals...
  ; The quantity "60" is represented as LX in roman numerals...

  ; The pattern here is that digits from 1-10 have similar properties
  ; in each decimal place. So a "1" in the ones place is "I" and it's a "X"
  ; in the tens place. A "5" is "V" in the ones place and it's an "L" in the
  ; tens place.

; -Methodology-
  ; The program works by recognizing what decimal place it's converting
  ; with a counter. Depending on the number of loops, the counter indicates
  ; the decimal place that is currently being converted. The counter will then
  ; be used to map out the arabic digit the appropriate "relative roman digit."

  ; The roman numbers are relative to the decimal place of the arabic digit
  ; currently being converted.
;--------------------------------------------------------------------

INCLUDE Irvine32.inc

.data
  dividend DWORD ?
  next_dividend DWORD ?
  divisor DWORD ?
  quotient DWORD ?
  cur_indx DWORD 0

  roman_one BYTE    'C', 'X', 'I'
  roman_five BYTE   'D', 'L', 'V'
  roman_ten BYTE    'M', 'C', 'X'

  prompt BYTE 'Enter a positive Arabic Numeral (0 to quit): ', 0
  prompt2 BYTE 'Values below 0 are not permitted, please try again.', 0

  ; Student info
  student_name BYTE 'Jahir F. Montes', 0
  r_number BYTE 'R11725642'
.code
main PROC
  ;--------------------------------------
  ; Code block to print student info

  ; Name
  mov edx, OFFSET student_name
  call WriteString
  call Crlf

  ; Rnumber
  mov edx, OFFSET r_number
  call WriteString
  call Crlf

  call Crlf
  ;--------------------------------------



  Top: ; Outerloop start point
  ;--------------------------------------
  ; Code block asking for user input
  mov edx, OFFSET prompt
  call WriteString

  ; Get arabic number to convert
  call readInt
  ;--------------------------------------



  ;--------------------------------------
  ; Code block to convert
  mov next_dividend, eax
  call ToRoman
  call Crlf
  call Crlf

  jmp Top
  ;--------------------------------------

  EndProgram: ; end of loop point
main ENDP
exit

StoreM PROC

  ; check if dividend is in 1000's
  mov eax, next_dividend
  mov edx, 0
  mov ebx, 1000
  div ebx

  ; store results
  mov ecx, eax ; quotient is loop count
  mov next_dividend, edx ; remainder is new dividend

  ; if quotient is 0, arabic numeral does not start with start with thousands
  cmp eax, 0
  je ExitP


  ; set up loop
  mov al, 'M'

  ; loop for every 1000
  L1:

    call WriteChar

  loop L1

  ; save new current index

  ExitP: RET
StoreM ENDP

ToRoman PROC
  ; Precondition: next_dividend is not 0

  ; Check if next_dividend is 0, return if true
  mov eax, next_dividend
  cmp eax, 0

  ; if not zero, check if negative
  jne checkNegative
  exit

  checkNegative:
  jl NegativeFound
  jmp StartProc

    NegativeFound:
    mov edx, OFFSET prompt2
    call Crlf
    call WriteString
    RET

  StartProc:

  ; Handle MSB's of 1000
  call StoreM

  ; divisor and dividend starts with 100's
  mov eax, 100
  mov divisor, eax

  ; recent indecies and loop counter
  mov ecx, 0 ; initialize loop counter
  mov cur_indx, ecx
  L1:
    ; setup division of dividend with divisor to get current msb
    mov eax, next_dividend
    mov edx, 0  ; 0 extend
    div divisor

    ; save results
    mov dividend, eax
    mov next_dividend, edx

    ; compare results
    cmp eax, 0  ; check if current MSB is 0
    je Shift    ; if it is, skip this iteration

    ;-------------------------------
    ; Code block for conversion

    Nine:
      mov eax, dividend
      cmp eax, 9
      jne five

      mov al, roman_one[ecx]
      call WriteChar
      mov al, roman_ten[ecx]
      call WriteChar

      jmp Shift
    Five:
      mov eax, dividend
      cmp eax, 5
      jl Four

      ; print initial 5
      mov ecx, cur_indx
      mov al, roman_five[ecx]
      call WriteChar

      ; store current roman One
      mov al, roman_one[ecx]

      ; get remaining one's
      mov ecx, dividend
      sub ecx, 5

      ; check for trailing one's
      cmp ecx, 0
      je Four

      ; print remaining one's
      Five1:

        call WriteChar

      loop Five1
      mov ecx, cur_indx

    Four:
      mov ecx, cur_indx

      mov eax, dividend
      cmp eax, 4
      jne One

      mov al, roman_one[ecx]
      call WriteChar
      mov al, roman_five[ecx]
      call WriteChar
    One:
      mov eax, dividend
      cmp eax, 3
      jnle Shift

      mov quotient, eax

      ; save current roman number to al
      mov ecx, cur_indx
      mov al, roman_one[ecx]

      ; quotient is counter for # of 1's
      mov ecx, quotient

      ; loop one's
      One1:

        call WriteChar

      loop One1

    ;-------------------------------

    Shift:
    ; shift divisor integer to right
    mov eax, divisor
    mov edx, 0
    mov ebx, 10
    div ebx
    mov divisor, eax

    mov ecx, cur_indx
    inc ecx
    mov cur_indx, ecx

  ; if divisor not 0, restart outer loop
  cmp eax, 0
  jne L1

  EndProc: RET
ToRoman ENDP

END main
