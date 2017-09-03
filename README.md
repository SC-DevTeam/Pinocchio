# Pinocchio

Welcome to Pinocchio, the all in one SC games protocol understanding and exploitation. Pinocchio is an open source project supported by the community. 
It's built with vanilla java, with all the boilerplates to allow an easy understanding of the code in the hope to have more people involved.

## Patchers

* Clash Royale patcher
* Clash of Clans patcher
* Boom Beach patcher
* HayDay patcher

## Proxies
* Clash Royale proxy
* Clash of Clans proxy
* Boom Beach proxy
* HayDay proxy

## Utilities
* Protocol definitions and parser

# TODO
* Port all the messages from the know ones to Pinocchio java mapper
* Make utils to rebuild messages

# How to start
```java -jar Pinocchio.jar```

# Commands
* help 

Show help

* patch [cr - coc - bb - hh - bs]

Patchers. BrawlStars will be done once it's released for Android. Extract libg from android device through adb, patch it (ONLY ARM) and upload it back.

* proxy [cr - coc - bb - hh - bs]

Proxies. BrawlStars will be done once it's released for Android. 

* hosts add [ip] [dns]
Pull hosts file from Android through adb, write it and upload back

* hosts remove [dns]
Pull hosts file from Android through adb, remove the dns and upload back

* hosts supercell
Print a list of supercell game dns