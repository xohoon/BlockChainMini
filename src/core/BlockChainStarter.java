package core;

import util.Util;

public class BlockChainStarter {

	public static void main(String[] args) {
		
		
		/**
		 * 
		 * BlockChain 실행기
		 * 
		 * **/
		
		int nonce = 0;
		while(true) {
			if(Util.getHash(nonce + "").substring(0, 6).equals("000000")) {
				System.out.println("정답 : " + nonce);
				
				break;
			}
			nonce++;
		}
		
	}

}
