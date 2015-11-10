# Rotor

Rotor is a stack-based golfing language inspired by CJam/GolfScript, Gibberish and Enema. Unlike most golfing languages that try to cram as many useful instructions as they can into one-byte instructions and the rest as two-bytes, Rotor uses a system of multiple instruction sets (called wheels) that can be switched between. For example, `N` does natural logarithm in the math wheel while in the string wheel it pushes a newline onto the stack.

Rotor is very much WIP - currently there are no CJam-style blocks and only one wheel (the math wheel). More to come later.

## Running

``groovy Rotor.groovy <FILE> <<< INPUT``

Use ``""`` for input if the program doesn't take any.
