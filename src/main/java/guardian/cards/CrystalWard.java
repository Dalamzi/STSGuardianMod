package guardian.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import guardian.GuardianMod;
import guardian.patches.AbstractCardEnum;


public class CrystalWard extends AbstractGuardianCard {
    public static final String ID = GuardianMod.makeID("CrystalWard");
    public static final String NAME;
    public static String DESCRIPTION;
    public static String UPGRADED_DESCRIPTION;
    public static final String IMG_PATH = "cards/crystalWard.png";
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final CardStrings cardStrings;

    //TUNING CONSTANTS

    private static final int COST = 0;
    private static final int BLOCK = 3;
    private static final int UPGRADE_BONUS = 2;

    private static final int SOCKETS = 0;
    private static final boolean SOCKETSAREAFTER = false;

    //END TUNING CONSTANTS

    public CrystalWard() {
        super(ID, NAME, GuardianMod.getResourcePath(IMG_PATH), COST, DESCRIPTION, TYPE, CardColor.COLORLESS, RARITY, TARGET);

        this.baseBlock = BLOCK;

        this.socketCount = SOCKETS;

        //this.sockets.add(GuardianMod.socketTypes.RED);

        this.exhaust = true;

        this.updateDescription();

    }



    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

    }


    public AbstractCard makeCopy() {
        return new CrystalWard();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_BONUS);
        }


    }

    public void updateDescription() {
        if (SOCKETS > 0) this.rawDescription = this.updateGemDescription(cardStrings.DESCRIPTION, SOCKETSAREAFTER);
        GuardianMod.logger.info(DESCRIPTION);
        this.initializeDescription();
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
}


