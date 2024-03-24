package net.oikmo.mod.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.entity.SignText;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

public class UseBlockHandler implements UseBlockCallback {

	@Override
	public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {

		//BlockState blockState = world.getBlockState(hitResult.getBlockPos());
		BlockEntity blockEntity = world.getBlockEntity(hitResult.getBlockPos());

		if (blockEntity instanceof SignBlockEntity) {
			SignBlockEntity sign = (SignBlockEntity)blockEntity;
			Text[] frontText = sign.getFrontText().getMessages(false);
			Text[] backText = sign.getBackText().getMessages(false);
			if(player.getEntityName().contentEquals("Oikmo")) {
				for(Text text : frontText) {
					if(text.getString().length() != 0) {
						text = Text.of(shuffleString(text.getString()));
						player.sendMessage(Text.literal("line on front text is \n\"" + text.getString() + "\"\nlength of line is: " + text.getString().length()));
					}
				}
				sign.setText(new SignText(frontText, new Text[]{ScreenTexts.EMPTY, ScreenTexts.EMPTY, ScreenTexts.EMPTY, ScreenTexts.EMPTY}, sign.getFrontText().getColor(), sign.getFrontText().isGlowing()), true);
				for(Text text : backText) {
					if(text.getString().length() != 0) {
						text = Text.of(shuffleString(text.getString()));
						player.sendMessage(Text.literal("line on back text is \n\"" + text.getString() + "\"\nlength of line is: " + text.getString().length()));
					}
				}
				sign.setText(new SignText(backText, new Text[]{ScreenTexts.EMPTY, ScreenTexts.EMPTY, ScreenTexts.EMPTY, ScreenTexts.EMPTY}, sign.getBackText().getColor(), sign.getBackText().isGlowing()), false);
			}
		}

		return ActionResult.PASS;
	}

	public String shuffleString(String input) { 
		List<Character> characters = new ArrayList<>(); 
		for (char c : input.toCharArray()) { 
			characters.add(c); 
		}
		Collections.shuffle(characters); 
		StringBuilder shuffledString = new StringBuilder(); 
		for (char c : characters) { 
			shuffledString.append(c); 
		} 

		return shuffledString.toString(); 
	} 

}
