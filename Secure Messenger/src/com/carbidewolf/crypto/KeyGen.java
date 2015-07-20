package com.carbidewolf.crypto;

import java.util.HashMap;

import gnu.crypto.Registry;
import gnu.crypto.key.IKeyPairGenerator;
import gnu.crypto.key.KeyPairGeneratorFactory;
import gnu.crypto.key.rsa.RSAKeyPairGenerator;
import gnu.crypto.prng.IRandom;
import gnu.crypto.prng.PRNGFactory;

public class KeyGen
{
	public void GenerateKeyPair()
	{
		IKeyPairGenerator kpg = KeyPairGeneratorFactory.getInstance(Registry.RSA_KPG);
		
		IRandom rand = PRNGFactory.getInstance("MDGenerator");
		
		HashMap map = new HashMap();
		//map.put(RSAKeyPairGenerator.SOURCE_OF_RANDOMNESS, value);
	}
}