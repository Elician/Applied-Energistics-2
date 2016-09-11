package appeng.client.render.crafting;


import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;

import appeng.block.crafting.BlockCraftingUnit;
import appeng.bootstrap.BlockRenderingCustomizer;
import appeng.bootstrap.IBlockRendering;
import appeng.bootstrap.IItemRendering;
import appeng.core.AppEng;


/**
 * Rendering customization for the crafting cube.
 */
public class CraftingCubeRendering extends BlockRenderingCustomizer
{

	private final String registryName;

	private final BlockCraftingUnit.CraftingUnitType type;

	public CraftingCubeRendering( String registryName, BlockCraftingUnit.CraftingUnitType type )
	{
		this.registryName = registryName;
		this.type = type;
	}

	@Override
	public void customize( IBlockRendering rendering, IItemRendering itemRendering )
	{
		ResourceLocation baseName = new ResourceLocation( AppEng.MOD_ID, registryName );

		// Disable auto-rotation
		rendering.modelCustomizer( ( loc, model ) -> model );

		// This is the standard blockstate model
		ModelResourceLocation defaultModel = new ModelResourceLocation( baseName, "normal" );

		// This is the built-in model
		String builtInName = "models/block/crafting/" + registryName + "/builtin";
		ModelResourceLocation builtInModelName = new ModelResourceLocation( new ResourceLocation( AppEng.MOD_ID, builtInName ), "normal" );

		rendering.builtInModel( builtInName, new CraftingCubeModel( type ) );

		rendering.stateMapper( block -> mapState( block, defaultModel, builtInModelName ) );
	}

	private Map<IBlockState, ModelResourceLocation> mapState( Block block, ModelResourceLocation defaultModel, ModelResourceLocation formedModel )
	{
		Map<IBlockState, ModelResourceLocation> result = new HashMap<>();
		for( IBlockState state : block.getBlockState().getValidStates() )
		{
			if( state.getValue( BlockCraftingUnit.FORMED ) )
			{
				// Always use the builtin model if the multiblock is formed
				result.put( state, formedModel );
			}
			else
			{
				// Use the default model
				result.put( state, defaultModel );
			}
		}
		return result;
	}
}
