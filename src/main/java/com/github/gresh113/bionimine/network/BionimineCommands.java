package com.github.gresh113.bionimine.network;

import com.github.gresh113.bionimine.Bionimine;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.tree.LiteralCommandNode;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class BionimineCommands {

	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralCommandNode<CommandSource> cmdTut = dispatcher.register(Commands.literal(Bionimine.MODID).then(CommandTest.register(dispatcher)));

		dispatcher.register(Commands.literal("tut").redirect(cmdTut));
	}
}
