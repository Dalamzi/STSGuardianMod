package guardian.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import guardian.GuardianMod;
import guardian.cards.SentryBeam;

public class DefensiveModeMoreBlock extends CustomRelic {
    public static final String ID = "Guardian:DefensiveModeMoreBlock";
    public static final String IMG_PATH = "relics/bronzeArmorPlate.png";
    public static final String OUTLINE_IMG_PATH = "relics/bronzeArmorPlateOutline.png";
    private static final int HP_PER_CARD = 1;

    public DefensiveModeMoreBlock() {
        super(ID, new Texture(GuardianMod.getResourcePath(IMG_PATH)), new Texture(GuardianMod.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.COMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }


    @Override
    public AbstractRelic makeCopy() {
        return new DefensiveModeMoreBlock();
    }
}