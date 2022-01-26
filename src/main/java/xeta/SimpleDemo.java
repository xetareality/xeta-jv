package xeta;

import xeta.library.crypto.Crypto;
import xeta.modules.Balance;
import xeta.modules.Transaction;
import xeta.modules.Transfer;
import xeta.modules.models.BalanceData;
import xeta.modules.models.TransactionData;
import xeta.modules.models.TransactionRequest;
import xeta.modules.models.TransferData;

import java.util.List;

public class SimpleDemo {

	/*
	1. Generating keypair
	2. Read a single transaction
	3. Read a single transfer
	4. Scan transfers (Xeta.transfer.scanFromCreated, Xeta.transfer.scanToCreated)
	5. Check balance for a address + token (Xeta.balance.readAddressToken)
	6. Create a transfer out (transfer.create)
	*/
	public static void main(String[] args) {
		// 1.
		System.out.println("1. Generating keypair:");
		final String[] keyPair = Crypto.generateKeyPair();
		System.out.println("public key: [" + keyPair[0] + "], private key: [" + keyPair[1] + "]");
		System.out.println();

		// 2.
		System.out.println("2. Read single transaction:");
		final TransactionData transaction = Transaction.read("ArKcBnKK8drVSjVfLpr8wMLqZ5XzBHrZGDhwDad1BuVA");
		System.out.println(transaction);
		System.out.println();

		// 3.
		System.out.println("3. Read a single transfer:");
		final TransferData transfer = Transfer.read("BLmbFUnUuDJPtXb6WJo4uUNtTSEKePgChBMgBN9HcTp6");
		System.out.println(transfer);
		System.out.println();

		// 4.
		System.out.println("4. Scan transfers");
		System.out.println("Xeta.transfer.scanFromCreated:");
		final List<TransferData> scanFromCreated = Transfer.scanFromCreated("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", null, null);
		System.out.println(scanFromCreated);
		System.out.println("Xeta.transfer.scanToCreated:");
		final List<TransferData> scanToCreated = Transfer.scanToCreated("8eVYQCDDXsMRaPpxgeUZDJZ6Kes454owbrjkgwV3Rmgs", null, null);
		System.out.println(scanToCreated);
		System.out.println();

		// 5.
		System.out.println("5. Check balance for a address + token (Xeta.balance.readAddressToken):");
		final BalanceData balanceAddressToken = Balance.readAddressToken("Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "11111111111111111111111111111xeta");
		System.out.println("Balance for address [5d1KYTwseBGzwuroT3wm5793pZ1fAXRmY4e9tBNErwYk] and token [11111111111111111111111111111xeta]:\n" + balanceAddressToken);
		System.out.println();

		// 6.
		System.out.println("6. Creating a transfer out:");
		final TransactionData transactionData = Transfer.create("8eVYQCDDXsMRaPpxgeUZDJZ6Kes454owbrjkgwV3Rmgs", "11111111111111111111111111111xeta", "5", "Ek7Na3CBuWBJtzXfTGoUXYfeXhH2NCVuuzkKQvSFiZvY", "for testing java client purposes", TransactionRequest.builder().build(), true);
		System.out.println(transactionData);
	}
}
