package appeng.integration.modules.jei;


import java.util.Collections;
import java.util.List;

import net.minecraft.item.ItemStack;

import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeRegistryPlugin;
import mezz.jei.api.recipe.IRecipeWrapper;

import appeng.api.AEApi;
import appeng.api.features.IInscriberRegistry;


/**
 * Exposes the inscriber registry recipes to JEI.
 */
class InscriberRegistryPlugin implements IRecipeRegistryPlugin
{

	private final IInscriberRegistry inscriber = AEApi.instance().registries().inscriber();

	@Override
	public <V> List<String> getRecipeCategoryUids( IFocus<V> focus )
	{
		if( !( focus.getValue() instanceof ItemStack ) )
		{
			return Collections.emptyList();
		}

		if( focus.getMode() == IFocus.Mode.INPUT )
		{
			ItemStack input = (ItemStack) focus.getValue();
			for( ItemStack validInput : inscriber.getInputs() )
			{

			}
		}

		return Collections.emptyList();
	}

	@Override
	public <T extends IRecipeWrapper, V> List<T> getRecipeWrappers( IRecipeCategory<T> recipeCategory, IFocus<V> focus )
	{
		return null;
	}
}
