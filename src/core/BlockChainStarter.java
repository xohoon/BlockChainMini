package core;

import java.util.ArrayList;

import util.Util;

public class BlockChainStarter {

	public static void main(String[] args) {
		
		
		/**
		 * 
		 * BlockChain 실행기
		 * 
		 * **/

		Block block1 = new Block(1, null, 0, new ArrayList());
		block1.mine();
		block1.showInformation();
		
		Block block2 = new Block(2, block1.getBlockHash(), 0, new ArrayList());
		block2.addTransaction(new Transaction("나동빈", "박한울", 1.5));
		block2.addTransaction(new Transaction("이태일", "박한울", 0.7));
		block2.mine();
		block2.showInformation();
		
		Block block3 = new Block(3, block2.getBlockHash(), 0, new ArrayList());
		block3.addTransaction(new Transaction("강종구", "이상국", 8.2));
		block3.addTransaction(new Transaction("박한울", "나동빈", 0.4));
		block3.mine();
		block3.showInformation();
		
		Block block4 = new Block(4, block3.getBlockHash(), 0, new ArrayList());
		block4.addTransaction(new Transaction("이상국", "강종구", 0.1));
		block4.mine();
		block4.showInformation();
		
	}

}
