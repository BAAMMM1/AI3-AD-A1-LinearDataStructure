package task4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Chris on 12.04.2018
 */
public class ExpressionAlgorithmusTest {

    private ExpressionAlgorithmus algo;

    @Before
    public void setup(){

        this.algo = new ExpressionAlgorithmus();

    }

    @Test
    public void canComputePlus(){
        assertEquals(2, algo.compute("(1+1)"));
        assertEquals(12, algo.compute("(11+1)"));
        assertEquals(12, algo.compute("(1+11)"));

        assertEquals(3, algo.compute("((1+1)+1)"));
        assertEquals(13, algo.compute("((1+1)+11)"));

        assertEquals(3, algo.compute("(1+(1+1))"));
        assertEquals(13, algo.compute("(11+(1+1))"));

        assertEquals(4, algo.compute("((1+1)+(1+1))"));
        assertEquals(14, algo.compute("((1+1)+(11+1))"));
        assertEquals(14, algo.compute("((1+1)+(1+11))"));

        assertEquals(5, algo.compute("(((1+1)+1)+(1+1))"));
        assertEquals(15, algo.compute("(((1+1)+11)+(1+1))"));
        assertEquals(15, algo.compute("(((1+1)+1)+(11+1))"));
        assertEquals(15, algo.compute("(((1+1)+1)+(1+11))"));

    }

    @Test
    public void canComputeMinus(){
        assertEquals(0, algo.compute("(1-1)"));
        assertEquals(10, algo.compute("(11-1)"));
        assertEquals(-10, algo.compute("(1-11)"));

        assertEquals(-1, algo.compute("((1-1)-1)"));
        assertEquals(-11, algo.compute("((1-1)-11)"));

        assertEquals(1, algo.compute("(1-(1-1))"));
        assertEquals(11, algo.compute("(11-(1-1))"));

        assertEquals(0, algo.compute("((1-1)-(1-1))"));
        assertEquals(-10, algo.compute("((1-1)-(11-1))"));
        assertEquals(10, algo.compute("((1-1)-(1-11))"));

        assertEquals(-1, algo.compute("(((1-1)-1)-(1-1))"));
        assertEquals(-11, algo.compute("(((1-1)-11)-(1-1))"));
        assertEquals(-11, algo.compute("(((1-1)-1)-(11-1))"));
        assertEquals(9, algo.compute("(((1-1)-1)-(1-11))"));
    }

    @Test
    public void canComputeMult(){
        assertEquals(1, algo.compute("(1*1)"));
        assertEquals(11, algo.compute("(11*1)"));
        assertEquals(11, algo.compute("(1*11)"));

        assertEquals(1, algo.compute("((1*1)*1)"));
        assertEquals(11, algo.compute("((1*1)*11)"));

        assertEquals(1, algo.compute("(1*(1*1))"));
        assertEquals(11, algo.compute("(11*(1*1))"));

        assertEquals(1, algo.compute("((1*1)*(1*1))"));
        assertEquals(11, algo.compute("((1*1)*(11*1))"));
        assertEquals(11, algo.compute("((1*1)*(1*11))"));

        assertEquals(1, algo.compute("(((1*1)*1)*(1*1))"));
        assertEquals(11, algo.compute("(((1*1)*11)*(1*1))"));
        assertEquals(11, algo.compute("(((1*1)*1)*(11*1))"));
        assertEquals(11, algo.compute("(((1*1)*1)*(1*11))"));
    }

    @Test
    public void canComputeDiv(){
        assertEquals(1, algo.compute("(1/1)"));
        assertEquals(11, algo.compute("(11/1)"));
        assertEquals(0, algo.compute("(1/11)"));

        assertEquals(1, algo.compute("((1/1)/1)"));
        assertEquals(0, algo.compute("((1/1)/11)"));

        assertEquals(1, algo.compute("(1/(1/1))"));
        assertEquals(11, algo.compute("(11/(1/1))"));

        assertEquals(1, algo.compute("((1/1)/(1/1))"));
        assertEquals(0, algo.compute("((1/1)/(11/1))"));
        assertEquals(1, algo.compute("((1/1)/(11/11))"));

        assertEquals(1, algo.compute("(((1/1)/1)/(1/1))"));
        assertEquals(0, algo.compute("(((1/1)/11)/(1/1))"));
        assertEquals(0, algo.compute("(((1/1)/1)/(11/1))"));
        assertEquals(1, algo.compute("(((1/1)/1)/(11/11))"));
    }

    @Test
    public void canComputeRandom(){
        assertEquals(-47, algo.compute("(((1+2)*3)-(7*8))"));
        assertEquals(-17, algo.compute("(((11+2)*3)-(7*8))"));
        assertEquals(40, algo.compute("((6*(4+2))+(5-1))"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeEmpty(){
        this.algo.compute("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantCompute2OnlyOpenBracket(){
        this.algo.compute("(");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeOnlyCloseBracket(){
        this.algo.compute(")");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeWithoutTwoOperandAndOperator(){
        this.algo.compute("()");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeWithOutOneOperandAndOperator(){
        this.algo.compute("(1)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeWithOutOperator(){
        this.algo.compute("(11)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeWithIncorrectOperator(){
        this.algo.compute("(1)1)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeOneOpenBrackenTooMuch(){
        this.algo.compute("((1+1)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeOneCloseBrackedTooMuch(){
        this.algo.compute("(1+1))");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeMoreOpenBracketsThanCloseBrackets(){
        this.algo.compute("((1(1)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeModeCloseBracketsThanOpenBrackets(){
        this.algo.compute("((1(1)))");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeNotEnoughBrackets(){
        this.algo.compute("(1+1)+12");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeWithoutOneCloseBracket(){
        this.algo.compute("((1+1)+12");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeBracketsSameSizeButNotEnough(){
        this.algo.compute("(1+1)+(1+1)");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeRandomWithIncorretOperator(){
        this.algo.compute("(((1+2)*3)-(7(8))");
    }

    @Test(expected = IllegalArgumentException.class)
    public void cantComputeRandomWithIncorrectOperand(){
        this.algo.compute("(((1+2)*3)-(7*+))");
    }









}