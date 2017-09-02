# Pinocchio

Welcome to Pinocchio, the all in one SC games protocol understanding and exploitation. Pinocchio is an open source project supported by the community.

## BIG WIP
Pinocchio is actually a big WIP project. It's built with vanilla java, with all the boilerplates to allow an easy understanding of the code in the hope to have more people involved.

# What's working:

* Clash Royale patcher
* Clash Royale proxy
* Protocol definitions and parser

# TODO
* Make proxy and patcher abstract for all the games
* Port all the messages from the know ones to Pinocchio java mapper

# How to start
```java -jar Pinocchio.jar```

# Commands
* help 

Show help

* patch [cr - coc - bb - hh - bs] [host] [key optional]

Patchers. Actually only patch cr would work. Extract libg from android device through adb, patch it and upload it back.

* proxy [cr - coc - bb - hh - bs]

Proxies. Actually only proxy cr would work. 