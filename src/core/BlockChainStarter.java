package core;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import util.EC;

public class BlockChainStarter {

	public static void main(String[] args) throws Exception {
		
		
		/**
		 * 
		 * BlockChain 실행기
		 * 
		 * **/
		
		Security.addProvider(new BouncyCastleProvider());
		Wallet wallet1 = new Wallet();
		wallet1.setFromFile("private1.pem", "public1.pem");
		Wallet wallet2 = new Wallet();
		wallet1.setFromFile("private2.pem", "public2.pem");
		Wallet wallet3 = new Wallet();
		wallet1.setFromFile("private3.pem", "public3.pem");
		
		Block block1 = new Block(1, null, 0, new ArrayList());
		block1.mine();
		block1.showInformation();
		
		Block block2 = new Block(2, block1.getBlockHash(), 0, new ArrayList());
		
		// 지갑1에서 지갑2로 코인을 전송했다는 의미를 가진 트랜잭션 생성
		Transaction transaction1 = new Transaction(wallet1, wallet2.getPublicKey(), 1.5, "2019-06-14 23:05:19.5");
		block2.addTransaction(transaction1);
		
		// 지갑2에서 지갑3으로 코인을 전송했다는 의미를 가진 트랜잭션 생성
		Transaction transaction2 = new Transaction(wallet2, wallet3.getPublicKey(), 1.5, "2019-06-15 23:05:19.5");
		block2.addTransaction(transaction2);
		
		block2.mine();
		block2.showInformation();
		
		Block block3 = new Block(3, block2.getBlockHash(), 0, new ArrayList());
		
		// 지갑1에서 지갑3으로 코인을 전송했다는 의미를 가진 트랜잭션을 생성
		Transaction transaction3 = new Transaction(wallet1, wallet3.getPublicKey(), 1.5, "2019-06-16 23:05:19.5");
		block2.addTransaction(transaction3);
		// 지갑2에서 지갑3으로 코인을 전송했다는 의미를 가진 트랜잭션을 생성
		Transaction transaction4 = new Transaction(wallet2, wallet3.getPublicKey(), 1.5, "2019-06-17 23:05:19.5");
		block2.addTransaction(transaction4);
		
		block3.mine();
		block3.showInformation();
		
		
		/*
		 * // 바운드시 캐슬의 암호화 라이브러리를 사용하도록 설정 Security.addProvider(new
		 * BouncyCastleProvider());
		 * 
		 * // 타원 곡선 객체를 생성해 개인키와 공개키를 각각 private.pem, public.pem 으로 저장 EC ec = new EC();
		 * ec.generate("private1.pem", "public1.pem"); ec.generate("private2.pem",
		 * "public2.pem");
		 * 
		 * 
		 * // 파일로 저장한 개인키와 공개키를 다시 프로그램으로 불러온다 PrivateKey privateKey1 =
		 * ec.readPrivateKeyFromPemFile("private1.pem"); PublicKey publicKey1 =
		 * ec.readPublicKeyFromPemFile("public1.pem"); PrivateKey privateKey2 =
		 * ec.readPrivateKeyFromPemFile("private2.pem"); PublicKey publicKey2 =
		 * ec.readPublicKeyFromPemFile("public2.pem");
		 * 
		 * Signature ecdsa; ecdsa = Signature.getInstance("SHA1withECDSA"); // 개인키 1을
		 * 이용해 암호화(서명) 합니다. ecdsa.initSign(privateKey1);
		 * 
		 * String text = "평문입니다."; System.out.println("평문 정보 : " + text); byte[] baText
		 * = text.getBytes("UTF-8");
		 * 
		 * 
		 * // 평문 데이터를 암호화하여 서명한 데이터를 출력 ecdsa.update(baText); byte[] baSignature =
		 * ecdsa.sign(); System.out.println("서명된 값 : 0x" + (new BigInteger(1,
		 * baSignature).toString(16)).toUpperCase());
		 * 
		 * Signature signature; signature = Signature.getInstance("SHA1withECDSA");
		 * 
		 * // 검을할 때는 공개키 2를 이용해 복호화를 수행합니다. signature.initVerify(publicKey2);
		 * signature.update(baText); boolean result = signature.verify(baSignature);
		 * 
		 * // 개인키와 매칭되는 공개키가 아니므로 복호화에 실채 System.out.println("신원 검증 : " + result);
		 */
	    
	    
	    
		
		/*
		 * // 무작위의 개인키와 공개키를 생성하기 위해 키 생성 객체를 정의 KeyPairGenerator kpg;
		 * 
		 * kpg = KeyPairGenerator.getInstance("EC", "SunEC");
		 * 
		 * // 타원 곡선 디지털 서명 알고리즘 객체를 생성 ECGenParameterSpec ecsp;
		 * 
		 * // 세부 알고리즘 스펙을 정의 ecsp = new ECGenParameterSpec("sect163k1");
		 * 
		 * // 랜덤으로 임의의 키 생성 kpg.initialize(ecsp, new SecureRandom());
		 * 
		 * //개인키와 공개키 한 쌍을 생성 KeyPair kp = kpg.genKeyPair(); PrivateKey privKey =
		 * kp.getPrivate(); PublicKey pubKey = kp.getPublic();
		 * 
		 * // 서명 객체를 생성해 개인키 설정 Signature ecdsa; ecdsa =
		 * Signature.getInstance("SHA1withECDSA", "SunEC"); ecdsa.initSign(privKey);
		 * 
		 * // 임의의 원래 문장을 정의 String text = "동빈이가 한율이에게 100코인 전송";
		 * System.out.println("원래 문장 : " + text);
		 * 
		 * String textInfected = "동빈이가 한율이에게 999999코인 전송";
		 * System.out.println("변경된 문장 : " + textInfected);
		 * 
		 * // 원래 문장에 대해 암호화를 수행해 서명 값(암호문)을 얻어낸다. ecdsa.update(text.getBytes("UTF-8"));
		 * byte[] signatureByte = ecdsa.sign(); System.out.println("암호문 : Ox" + (new
		 * BigInteger(1, signatureByte).toString(16)).toUpperCase());
		 * 
		 * // 서명 객체를 생성해 공개키를 이용해 복호화 할 수 있도록 설정 Signature signature; signature =
		 * Signature.getInstance("SHA1withECDSA", "SunEC");
		 * signature.initVerify(pubKey);
		 * 
		 * // 원래 문장을 공개키로 복호화해 검증 signature.update(text.getBytes("UTF-8"));
		 * System.out.println("원래 문장 검증 : " + signature.verify(signatureByte));
		 * 
		 * // 변경된 문장을 공개키로 검증 signature.update(textInfected.getBytes("UTF-8"));
		 * System.out.println("변경된 문장 검증 : " + signature.verify(signatureByte));
		 */
		
		
		
		
		
		
		
		
		/*
		 * Block block1 = new Block(1, null, 0, new ArrayList()); block1.mine();
		 * block1.showInformation();
		 * 
		 * Block block2 = new Block(2, block1.getBlockHash(), 0, new ArrayList());
		 * block2.addTransaction(new Transaction("나동빈", "박한울", 1.5));
		 * block2.addTransaction(new Transaction("이태일", "박한울", 0.7)); block2.mine();
		 * block2.showInformation();
		 * 
		 * Block block3 = new Block(3, block2.getBlockHash(), 0, new ArrayList());
		 * block3.addTransaction(new Transaction("강종구", "이상국", 8.2));
		 * block3.addTransaction(new Transaction("박한울", "나동빈", 9.9)); block3.mine();
		 * block3.showInformation();
		 * 
		 * Block block4 = new Block(4, block3.getBlockHash(), 0, new ArrayList());
		 * block4.addTransaction(new Transaction("이상국", "강종구", 0.1)); block4.mine();
		 * block4.showInformation();
		 */
		
	}

}
