package guardian.powers;


import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import guardian.actions.PlaceActualCardIntoStasis;
import guardian.actions.PlaceRandom1CostIntoStasis;
import guardian.actions.PlaceTopCardIntoStasisAction;
import guardian.actions.ReduceRightMostStasisAction;
import guardian.orbs.StasisOrb;


public class AutomayhemPower extends AbstractGuardianPower {
    public static final String POWER_ID = "Guardian:AutomayhemPower";
    public static PowerType POWER_TYPE = PowerType.BUFF;

    public static String[] DESCRIPTIONS;
    private AbstractCreature source;


    public AutomayhemPower(AbstractCreature owner, AbstractCreature source, int amount) {

        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        this.setImage("Orbwalk84.png", "Orbwalk32.png");
        this.type = POWER_TYPE;
        this.amount = amount;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;

        updateDescription();

    }

    public void updateDescription() {
        if (this.amount == 1){
            this.description = DESCRIPTIONS[0];

        } else {
            this.description = this.amount + DESCRIPTIONS[1];

        }

    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        super.atEndOfTurn(isPlayer);
        if (isPlayer){
            if (AbstractDungeon.player.orbs.size() > 0){
                this.flash();
                for (int i = 0; i < this.amount; i++) {
                    AbstractDungeon.actionManager.addToBottom(new ReduceRightMostStasisAction());

                }

            }
        }
    }
}