//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package guardian.events;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import com.megacrit.cardcrawl.events.AbstractImageEvent;
import com.megacrit.cardcrawl.localization.EventStrings;

import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import guardian.GuardianMod;

import java.util.ArrayList;

public class GemMine extends AbstractImageEvent {
    public static final String ID = "Guardian:GemMine";
    private static final EventStrings eventStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;
    public static final String[] OPTIONS;
    private static final String DIALOG_START;
    private static final String DIALOG_LEAVE;
    private static final String DIALOG_MINE;
    private static final String DIALOG_LEAVEWITHGEM;
    private int screenNum = 0;
    private boolean tookGems = false;
    private int damage;

    public GemMine() {
        super(NAME, DIALOG_START, GuardianMod.getResourcePath("/events/gemMine.jpg"));
        if (AbstractDungeon.ascensionLevel >= 15) {
            this.damage = (int)((float)AbstractDungeon.player.maxHealth * .09F);
        } else {
            this.damage = (int)((float)AbstractDungeon.player.maxHealth * 0.06F);
        }
        this.imageEventText.updateBodyText(DIALOG_START);
        this.imageEventText.setDialogOption(OPTIONS[0] + this.damage + OPTIONS[1]);
        this.imageEventText.setDialogOption(OPTIONS[2]);

    }

    public void onEnterRoom() {
        if (Settings.AMBIANCE_ON) {
            CardCrawlGame.sound.play("EVENT_GOLDEN");
        }

    }

    protected void buttonEffect(int buttonPressed) {
        switch(this.screenNum) {
            case 0:
                switch(buttonPressed) {
                    case 0:
                        this.imageEventText.updateBodyText(DIALOG_MINE);

                        ArrayList<AbstractCard> gems = GuardianMod.getRewardGemCards(false, 1);
                        AbstractCard card = gems.get(0);

                        AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(card, (float)(Settings.WIDTH / 2), (float)(Settings.HEIGHT / 2)));
                        CardCrawlGame.sound.play("MONSTER_BOOK_STAB_0");
                        AbstractDungeon.player.damage(new DamageInfo((AbstractCreature)null, this.damage));
                        this.tookGems = true;
                        return;
                    default:
                        if (this.tookGems){
                            this.imageEventText.updateBodyText(DIALOG_LEAVEWITHGEM);

                        } else {
                            this.imageEventText.updateBodyText(DIALOG_LEAVE);

                        }
                        this.screenNum = 1;
                        this.imageEventText.updateDialogOption(0, OPTIONS[2]);
                        this.imageEventText.clearRemainingOptions();
                        break;
                }
            case 1:
                this.openMap();
                break;
        }

    }

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString(ID);
        NAME = eventStrings.NAME;
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
        OPTIONS = eventStrings.OPTIONS;
        DIALOG_MINE = DESCRIPTIONS[1];
        DIALOG_LEAVE = DESCRIPTIONS[2];
        DIALOG_START = DESCRIPTIONS[0];
        DIALOG_LEAVEWITHGEM = DESCRIPTIONS[3];
    }
}
