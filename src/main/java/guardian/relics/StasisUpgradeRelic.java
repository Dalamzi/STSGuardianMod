package guardian.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.defect.DecreaseMaxOrbAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import guardian.GuardianMod;

public class StasisUpgradeRelic extends CustomRelic {
    public static final String ID = "Guardian:StasisUpgradeRelic";
    public static final String IMG_PATH = "relics/cryoChamber.png";
    public static final String OUTLINE_IMG_PATH = "relics/cryoChamberOutline.png";
    private static final int HP_PER_CARD = 1;

    public StasisUpgradeRelic() {
        super(ID, new Texture(GuardianMod.getResourcePath(IMG_PATH)), new Texture(GuardianMod.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.RARE, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public AbstractRelic makeCopy() {
        return new StasisUpgradeRelic();
    }
}