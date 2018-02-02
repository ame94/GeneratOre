package ca.ame94.generatore.util.types;

import ca.ame94.generatore.anno.Untested;
import ca.ame94.generatore.util.Logger;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

public class SignData {

    private Block blockRef = null;

    private String line1 = "";
    private String line2 = "";
    private String line3 = "";
    private String line4 = "";

    private SignType type = SignType.INVALID;


    public Block getBlockRef() {
        return blockRef;
    }

    public String getLine1() { return line1; }
    public String getLine2() { return line2; }
    public String getLine3() { return line3; }
    public String getLine4() { return line4; }

    private String truncate(String data) {
        String result = data;
        if (data.length() > 15) {
            result = data.substring(0, 15);
        }
        return result;
    }

    public void setLine(int lineNum, String data) {
        ((Sign)blockRef.getState()).setLine(lineNum, truncate(data));
    }

    public SignType getType() {
        return type;
    }

    /**
     * Assumes the given block is a sign and holds its data
     * @param signBlock
     */
    @Untested
    public SignData(Block signBlock) {

        Material blockType = signBlock.getType();
        if (blockType == Material.SIGN_POST) {
            type = SignType.POSTSIGN;
        } else if (blockType == Material.WALL_SIGN) {
            type = SignType.WALLSIGN;
        } else {
            type = SignType.INVALID;
        }

        if (type == SignType.POSTSIGN || type == SignType.WALLSIGN) {
            blockRef = signBlock;
            line1 = ((Sign)signBlock.getState()).getLine(0);
            line2 = ((Sign)signBlock.getState()).getLine(1);
            line3 = ((Sign)signBlock.getState()).getLine(2);
            line4 = ((Sign)signBlock.getState()).getLine(3);
        }
    }
}
