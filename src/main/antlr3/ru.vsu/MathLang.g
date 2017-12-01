grammar MathLang;

options {
  language=Java;
  output=AST;

  backtrack=true;
  memoize=true;
}

tokens {
  UNKNOWN                   ;
  DECL      =   'var'       ; //
  IF        =   'if'        ; //
  FOR       =   'for'       ; //
  FUNCTION  =   'function'  ; //
  FUNC_CALL				    ; //
  RETURN    =   'return'    ; //
  WHILE     =   'while'     ; //
  DO        =   'do'	    ; //
  INCR				        ; //
  DECR				        ; //
  ARRAY				        ; //
  ARR_CALL			        ; //
  BLOCK                     ; //
  PARAMS                    ; //
  ARGS				        ; //
}


@parser::header { package ru.vsu; }
@lexer::header { package ru.vsu; }


WHITESPACE :
    ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+    { $channel = HIDDEN; }
;

SL_COMMENT:
  '//' (options { greedy = false; }: .)* '\r'? '\n' { $channel=HIDDEN; }
;

ML_COMMENT:
  '/*' (options { greedy = false; }: .)* '*/' { $channel=HIDDEN; }
;



NUMBER: ('0'..'9')+ ('.' ('0'..'9')+)? //
;
IDENT:  ( 'a'..'z' | 'A'..'Z' | '_' )
        ( 'a'..'z' | 'A'..'Z' | '_' | '0'..'9' )* //
;
STRING: '"'(.)*'"'; //
ADD:    '+'     ; //
SUB:    '-'     ; //
MUL:    '*'     ; //
DIV:    '/'     ; //
MOD:	'%'		; //

GE:       '>='  ; //
LE:       '<='  ; //
NEQUALS:  '!='  ; //
EQUALS:   '=='  ; //
GT:       '>'   ; //
LT:       '<'   ; //

OR:      '||'   ; //
AND:      '&&'   ; //

ASSIGN: '='     ; //

NOT: '!'		; //


group:
  '('! term ')'!
    | NUMBER
    | IDENT
;


mult: group ( ( MUL | DIV | MOD )^ group )*  ;
add:  (mult|STRING)  ( ( ADD | SUB )^ (mult|STRING)  )*  ;
compare: (add ( ( GE | LE | NEQUALS | EQUALS | GT | LT)^ add )?) | not ;
and:  compare (AND^ compare )* ;
or: and (OR^ and )* ;
term: or | STRING | array | func_decl | func_call | array_call;

not: '!' term -> ^(NOT term);

formal_params: ( IDENT (',' IDENT)* )? -> ^(PARAMS IDENT*)  ;
func_decl:
  FUNCTION^ IDENT '('! formal_params ')'!
  '{'! exprList '}'!
;

function_args: ( arg (',' arg)* )? -> ^(ARGS arg*)  ;
func_call:
	    IDENT '(' function_args ')' -> ^(FUNC_CALL IDENT function_args)
;

var_decl:
	DECL^ IDENT
;

incr:
	(IDENT '++') -> ^(INCR IDENT)
;
decr:
	(IDENT '--') -> ^(DECR IDENT)
;

arg:
    term|IDENT
;

array_args:
    (arg (','! arg)* )?
;
array:
    '[' array_args ']'-> ^(ARRAY array_args)
;

array_call:
        IDENT('['arg']') -> ^(ARR_CALL IDENT arg)
;

expr1:
  (IDENT | var_decl) ASSIGN^ (term)
| incr | decr
| var_decl
| RETURN^ term?
| func_call
|array_call
;

expr2:
  '{'! exprList '}'!
| DO expr WHILE '(' term ')' -> ^(DO expr term)
| WHILE^ '('! term ')'! expr
| IF^ '('! term ')'! expr ( 'else'! expr )?
| FOR^ '('! expr1 ';'! or ';'! expr1 ')'! expr
| func_decl
;

expr:
  expr1 (';'!)+
| expr2
;

exprList:
  ( expr )* -> ^(BLOCK expr*)
;

result: exprList EOF!;

public execute:
  result
;
