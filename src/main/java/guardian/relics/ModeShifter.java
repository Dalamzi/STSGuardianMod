package guardian.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import guardian.GuardianMod;
import guardian.powers.ConstructPower;
import guardian.powers.ModeShiftPower;

public class ModeShifter extends CustomRelic {
    public static final String ID = "Guardian:ModeShifter";
    public static final String IMG_PATH = "relics/minion.png";
    public static final String OUTLINE_IMG_PATH = "relics/minionOutline.png";
    private static final int HP_PER_CARD = 1;

    public ModeShifter() {
        super(ID, new Texture(GuardianMod.getResourcePath(IMG_PATH)), new Texture(GuardianMod.getResourcePath(OUTLINE_IMG_PATH)),
                RelicTier.STARTER, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public void atBattleStartPreDraw() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ModeShiftPower(AbstractDungeon.player, AbstractDungeon.player,1),1));

    }

    @Override
    public AbstractRelic makeCopy() {
        return new ModeShifter();
    }

}