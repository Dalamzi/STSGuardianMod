package guardian.cards;



import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
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
import guardian.powers.DefenseModePower;
import guardian.powers.DefensiveModeBuffsPower;

public class SpikyScales extends AbstractGuardianCard {
    public static final String ID = GuardianMod.makeID("SpikyScales");
    public static final String NAME;
    public static final String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/spikyScales.png";

    private static final CardStrings cardStrings;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    //TUNING CONSTANTS

    private static final int COST = 1;
    private static final int THORNS = 2;
    private static final int UPGRADE_THORNS = 1;
    private static final int SOCKETS = 0;
    private static final boolean SOCKETSAREAFTER = true;

    //END TUNING CONSTANTS

    public SpikyScales() {
        super(ID, NAME, GuardianMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, AbstractCardEnum.GUARDIAN, RARITY, TARGET);

        this.socketCount = SOCKETS;
        this.updateDescription();
        this.magicNumber = this.baseMagicNumber = THORNS;
        
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p,m);
        if (p.hasPower(DefensiveModeBuffsPower.POWER_ID)){
            ((DefensiveModeBuffsPower)p.getPower(DefensiveModeBuffsPower.POWER_ID)).thorns += this.magicNumber;
            p.getPower(DefensiveModeBuffsPower.POWER_ID).flash();
        } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DefensiveModeBuffsPower(p, p,this.magicNumber,0,0)));
        }
        if (p.hasPower(DefenseModePower.POWER_ID)){
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber), this.magicNumber));
        }
        AbstractDungeon.actionManager.addToBottom(new SwitchToDefenseModeAction(p));

    }

    public AbstractCard makeCopy() {
        return new SpikyScales();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_THORNS);
        }
    }

    public void updateDescription() {
        if (SOCKETS > 0) this.rawDescription = this.updateGemDescription(cardStrings.DESCRIPTION, SOCKETSAREAFTER);
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


