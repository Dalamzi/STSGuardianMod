package guardian.powers;


import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import guardian.actions.PlaceActualCardIntoStasis;
import guardian.cards.TimeBomb;


public class ClonePower extends AbstractGuardianPower {
    public static final String POWER_ID = "Guardian:ClonePower";

    public static PowerType POWER_TYPE = PowerType.BUFF;

    public static String[] DESCRIPTIONS;

    public ClonePower(AbstractCreature owner, int amount) {

       this.ID = POWER_ID;
        this.owner = owner;

        this.type = POWER_TYPE;
        this.amount = amount;
        this.setImage("ClonePower84.png", "ClonePower32.png");

        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;

        updateDescription();

    }

    public void updateDescription() {
        if (this.amount != 1){
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        } else {
            this.description = DESCRIPTIONS[0];
        }

    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        super.onUseCard(card, action);
        if (!(card instanceof TimeBomb) && !card.exhaust && (card.type == AbstractCard.CardType.ATTACK || card.type == AbstractCard.CardType.SKILL)) {
            if (this.amount == 1) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));

            } else {
                this.amount -= 1;
            }

                AbstractDungeon.actionManager.addToBottom(new PlaceActualCardIntoStasis(card));

        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this));

    }
}
