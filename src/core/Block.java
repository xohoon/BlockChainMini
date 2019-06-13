package core;

import java.util.ArrayList;

import util.Util;

public class Block {
	
	private int blockID;
	private int nonce;
	private String previousBlockHash;
	private ArrayList transactionList;
	
	public int getBlockID() {
		return blockID;
	}
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	public int getNonce() {
		return nonce;
	}
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
	public String getPreviousBlockHash() {
		return previousBlockHash;
	}
	public void setPreviousBlockHash(String previousBlockHash) {
		this.previousBlockHash = previousBlockHash;
	}
	
	public Block(int blockID, String previousBlockHash, int nonce, ArrayList transactionList) {
		this.blockID = blockID;
		this.previousBlockHash = previousBlockHash;
		this.nonce = nonce;
		this.transactionList = transactionList;
	}
	
	public void addTransaction(Transaction transaction) {
		transactionList.add(transaction);
	}
	
	public void showInformation() {
		System.out.println("-------------------------------------------------");
		System.out.println("Block Num : " + getBlockID());
		System.out.println("pre Hash : " + getPreviousBlockHash());
		System.out.println("Mining  variable : " + getNonce());
		System.out.println("Transaction amount : " + transactionList.size() + "개");
		for(int i=0; i<transactionList.size(); i++) {
			System.out.println(((Transaction)transactionList.get(i)).getInformation());
		}
		System.out.println("Block Hash : " + getBlockHash());
		System.out.println("-------------------------------------------------");
	}
	
	public String getBlockHash() {
		String transactionInformations = "";
		for(int i=0; i<transactionList.size(); i++) {
			transactionInformations += ((Transaction) transactionList.get(i)).getInformation();
		}
		return Util.getHash(nonce + transactionInformations + previousBlockHash);
	}
	
	public void mine() {
		while(true) {
			if(getBlockHash().substring(0, 4).equals("0000")){
				System.out.println(blockID + "번째 블록 채굴에 성공했습니다.");
				break;
			}
			nonce++;
		}
	}
	
	
	
	
}
