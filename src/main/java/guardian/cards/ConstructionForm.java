package guardian.cards;



import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import guardian.GuardianMod;
import guardian.actions.SwitchToDefenseModeAction;
import guardian.patches.AbstractCardEnum;
import guardian.powers.ConstructModePower;

public class ConstructionForm extends AbstractGuardianCard {
    public static final String ID = GuardianMod.makeID("ConstructionForm");
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/constructionForm.png";

    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    //TUNING CONSTANTS

    private static final int COST = 3;
    private static final int TURNS = 2;
    private static final int UPGRADE_TURNS = 1;
    private static final int SOCKETS = 0;
    private static final boolean SOCKETSAREAFTER = true;

    //END TUNING CONSTANTS

    public ConstructionForm() {
        super(ID, NAME, GuardianMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.GUARDIAN, RARITY, TARGET);

        this.socketCount = SOCKETS;
        this.updateDescription();
        this.magicNumber = this.baseMagicNumber = TURNS;
        
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p,m);
        AbstractDungeon.effectsQueue.add(new com.megacrit.cardcrawl.vfx.BorderFlashEffect(com.badlogic.gdx.graphics.Color.GOLD, true));

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ConstructModePower(p, this.magicNumber), this.magicNumber));
        
    }

    public AbstractCard makeCopy() {
        return new ConstructionForm();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_TURNS);
        }
    }

    public void updateDescription() {
        if (this.socketCount > 0) this.rawDescription = this.updateGemDescription(cardStrings.DESCRIPTION, SOCKETSAREAFTER);
        //GuardianMod.logger.info(DESCRIPTION);
        this.initializeDescription();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
}


