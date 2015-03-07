package model;

/**
 * Simplifies the interface of a ViewOptions by only allowing these read methods.
 * @author Owner
 *
 */
public interface ViewUpdate{
	public int getPaletteB();
	public int getPaletteG();
	public int getPaletteR();
	public int getPaletteIndex();
	public boolean isClear();
	public boolean isClearStamps();
}

