620068192
Javon Davis

HPL to SVG translator
Done using the element based approached as described in the problem description.
Issues:
Cannot be used to represent libraries
Wait statement in Hpl not represented in svg(maybe could have been represented if done using a script based implementation)

Notes:
Every paint statement is kept in the svg file even though only the last paint statement at the top level is the one that will be displayed on screen, which is the expected behaviour.

Declaration.java used to add an extra method to the Compiler Result interface, this class was used to respresent assignments, hence results that could be assigned to a variable was made to extend this instead of implelmenting CompilerResult. 

Added methods to HPLContext interface to allow for the management of CompilerResult objects. 

Tests:
2 test files in src folder to demonstrate correct translation, these files are test3.hpl and fib-spiral.hpl and their svg equivalent is also present.

