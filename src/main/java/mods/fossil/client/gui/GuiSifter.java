package mods.fossil.client.gui;

import mods.fossil.Fossil;
import mods.fossil.guiBlocks.ContainerSifter;
import mods.fossil.guiBlocks.TileEntitySifter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSifter extends GuiContainer
{
    private static final ResourceLocation furnaceGuiTextures = new ResourceLocation(Fossil.modid + ":" + "textures/gui/Sifter.png");
    private TileEntitySifter furnaceInventory;

    public GuiSifter(InventoryPlayer par1InventoryPlayer, TileEntitySifter par2TileEntitySifter)
    {
        super(new ContainerSifter(par1InventoryPlayer, par2TileEntitySifter));
        this.furnaceInventory = par2TileEntitySifter;
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.furnaceInventory.isInvNameLocalized() ? this.furnaceInventory.getInvName() : I18n.getString(this.furnaceInventory.getInvName());
        this.fontRenderer.drawString(s, this.xSize / 8 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        //this.fontRenderer.drawString(I18n.getString("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int halfwidth = (this.width - this.xSize) / 2;
        int halfheight = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(halfwidth, halfheight, 0, 0, this.xSize, this.ySize);
        int scalar;

        
        if (this.furnaceInventory.isBurning())
        {
            scalar = this.furnaceInventory.getBurnTimeRemainingScaled(12);
            //this.drawTexturedModalRect(halfwidth + 70, halfheight + 44 - scalar, 176, 12 - scalar, 14, scalar + 2);
            //this.drawTexturedModalRect(halfwidth + 75, halfheight + 33, 181, 2, 31, 31);
        }

        scalar = this.furnaceInventory.getCookProgressScaled(26);
        //this.drawTexturedModalRect(halfwidth + 79, halfheight + 34, 176, 14, scalar + 1, 16);
        this.drawTexturedModalRect(halfwidth + 75, halfheight + 33, 181, 2, 31, scalar + 1);
        
    }
}
