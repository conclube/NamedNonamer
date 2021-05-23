package me.conclure.nonamer.command;

import net.dv8tion.jda.api.JDA;

import me.conclure.nonamer.command.intercept.CommandInterceptor;
import me.conclure.nonamer.command.parse.CommandParser;
import me.conclure.nonamer.command.listener.ConsoleCommandListener;
import me.conclure.nonamer.command.listener.DiscordCommandListener;

public class CommandManager {
  private final CommandInterceptor interceptor;
  private final CommandParser parser;
  private final ConsoleCommandListener consoleCommandListener;
  private final DiscordCommandListener discordCommandListener;

  public CommandManager(JDA jda) {
    this.interceptor = new CommandInterceptor(this);
    this.parser = new CommandParser();
    this.consoleCommandListener = new ConsoleCommandListener(this);
    this.discordCommandListener = new DiscordCommandListener(jda, this);
  }

  public CommandInterceptor interceptor() {
    return this.interceptor;
  }

  public CommandParser parser() {
    return this.parser;
  }

  public void shutdown() {
    this.discordCommandListener.shutdown();
    this.consoleCommandListener.shutdown();
  }
}
