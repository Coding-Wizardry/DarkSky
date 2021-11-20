/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package vazkii.botania.client.patchouli.processor;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

import vazkii.botania.client.patchouli.PatchouliUtils;
import vazkii.botania.common.crafting.ModRecipeTypes;
import vazkii.patchouli.api.IComponentProcessor;
import vazkii.patchouli.api.IVariable;
import vazkii.patchouli.api.IVariableProvider;

public class PetalApothecaryProcessor implements IComponentProcessor {
	protected Recipe<?> recipe;

	@Override
	public void setup(IVariableProvider variables) {
		ResourceLocation id = new ResourceLocation(variables.get("recipe").asString());
		this.recipe = PatchouliUtils.getRecipe(ModRecipeTypes.PETAL_TYPE, id);
	}

	@Override
	public IVariable process(String key) {
		if (recipe == null) {
			return null;
		}
		return switch (key) {
			case "recipe" -> IVariable.wrap(recipe.getId().toString());
			case "output" -> IVariable.from(recipe.getResultItem());
			case "heading" -> IVariable.from(recipe.getResultItem().getHoverName());
			default -> null;
		};
	}
}
