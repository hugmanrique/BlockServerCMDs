# BlockServerCMDs
### The solution to all the AuthMe and BungeeCord users

This plugin allows you to block any BungeeCord or Spigot command when a player is in a configurable list of servers.

This plugin can be used in all the Cracked/No Premium login servers replacing the crap ton of plugins used to block all the commands from this server (That usually uses AuthMe or another kind of auth plugin that doesn't offer a BungeeCord command blocker).

## Installation
You can go to [Spigot](https://www.spigotmc.org/resources/blockservercmds.9349/) for already compiled JARs.

You will need to clone this repo and package it with Maven:
```shell
git clone git@github.com:hugmanrique/BlockServerCMDs.git
cd BlockServerCMDs
mvn package
```

You will find your packaged JAR inside of the target folder. You can move this file into your BungeeCord's `/plugins` folder.

Run your server once and a config file will be generated. Edit the `config.yml` to your needs and then restart your proxy.

## Config File

```yaml
#List of the servers where commands will be blocked
servers:
- lobby1
- security
#Message sent when the people can not send the message
msg: "&cYou can't do this in this server"
#Do you want the chat to be blocked too?
block-chat: true
#List of allowed commands
whitelist:
- login
- register
- yolo
```

## Additional Info:
You can find all the complete features in the main [Spigot](https://www.spigotmc.org/resources/blockservercmds.9349/) page.