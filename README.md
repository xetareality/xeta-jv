# Official Java Client for Xeta

Official Java client to interact with Xeta Blockchain and Xeta Blockchain Interface.

Xeta is a serverless layer-1 blockchain for Metaverse, Gaming, and NFT applications that provides infinite scalability, high throughput, sub-second confirmation times, and fees at a tenth of a cent. Xeta achieves this by leveraging serverless compute and storage cloud services while innovating incentive structures and extending the Byzantine Fault Tolerance consensus mechanism for scalability.

# General

Download the latest release and add library jar to your classpath, import it to project, generate a public/private key (or use your existing ones) and start building using the examples below.

```
# Generate and connect a keypair
final String[] keyPair = Crypto.generateKeyPair();
Wallet.init(keyPair[0], keyPair[1]);
```

# Interface

The interface methods allow to interact with storage nodes for read-only functionality. Using these methods, you could build a similar frontend app like our [**network explorer**](https://xeta.network). Interface requests are free, but rate-limited and should allow for "regular" usage. Please contact us at developers@xetareality.com if you would like to have dedicated limits.

## Transaction

```
Transaction.poll(hash, interval, timeout);
Transaction.read(hash);
Transaction.list(hashes);
Transaction.scanSenderCreated(sender);
Transaction.scanPeriodCreated(period, created, hash);
```

## Transfer

```
Transfer.read(hash);
Transfer.list(hashes);
Transfer.scanSenderCreated(sender, created, hash);
Transfer.scanFromCreated(fromAddress, created, hash);
Transfer.scanToCreated(to, created, hash);
Transfer.scanTokenCreated(token, created, hash);
Transfer.scanFromTokenCreated(fromAddress, token, created, hash);
Transfer.scanToTokenCreated(to, token, created, hash);
```

## Token

```
Token.read(address);
Token.list(addresses);
Token.scanCreatorCreated(creator, created, address);
Token.scanNameCreated(name, created, address);
Token.scanSymbolCreated(symbol, created, address);
Token.scanOwnerCreated(owner, created, address);
Token.scanContentCreated(content, created, address);
Token.scanOwnerCategoryCreated(owner, category, created, address);
Token.scanCreatorCategoryCreated(creator, category, created, address);
```

## Pool

```
Pool.instance(address);
Pool.read(address);
Pool.list(addresses);
Pool.scanTokenProgramCreated(token, program, created, address);
Pool.scanNameCreated(name, created, address);
Pool.scanCreatorCreated(creator, created, address);
Pool.scanProgramCreated(program, created, address);
Pool.scanProgramExpires(program, created, address);
Pool.scanProgramNumber(program, number, address);
Pool.scanProgramXetaBalance(program, xetaBalance, address);
Pool.scanProgramTokenBalance(program, tokenBalance, address);
Pool.scanProgramTransfersCount(program, transfersCount, address);
```

## Address

```
Address.read(address);
```

## Allowance

```
Allowance.read(hash);
Allowance.list(hashes);
Allowance.readAddressSpenderToken(address, spender, token);
Allowance.scanAddressCreated(address, created, hash);
Allowance.scanSpenderCreated(spender, created, hash);
```

## Balance

```
Balance.read(hash);
Balance.list(hashes);
Balance.readAddressToken(address, token);
Balance.scanAddressAmount(address, amount, hash);
Balance.scanTokenAmount(token, amount, hash);
```

## Candle

```
Candle.read(interval, token, time);
Candle.scanIntervalTokenTime(interval, token, time);
Candle.scanIntervalTimeTurnover(interval, time, turnover, key);
Candle.scanIntervalTimeChange(interval, time, change, key);
```

## Claim

```
Claim.read(hash);
Claim.list(hashes);
Claim.scanHolderCategoryCreated(holder, category, created, hash);
Claim.scanIssuerCategoryCreated(issuer, category, created, hash);
Claim.scanIssuerAnswer(issuer, answer, hash);
Claim.scanIssuerNumber(issuer, number, hash);
Claim.scanIssuerTokenAmount(issuer, tokenAmount, hash);
Claim.scanIssuerXetaAmount(issuer, xetaAmount, hash);
Claim.scanIssuerCreated(issuer, created, hash);
Claim.scanHolderCreated(holder, created, hash);
Claim.scanIssuerTokenCreated(issuer, token, created, hash);
Claim.scanHolderTokenCreated(holder, token, created, hash);
Claim.scanIssuerHolder(issuer, holder, created, hash);
Claim.scanIssuerHolderToken(issuer, holder, token, created, hash);
```

## Registry

```
Registry.read(hash);
Registry.list(hashes);
Registry.scanContentCreated(content, created, token);
Registry.scanFingerprintCreated(fingerprint, created, token);
Registry.scanClusterCreated(cluster, created, token);
```

## Search

```
Search.search(query);
```

## Statistic

```
Statistic.read(key, time);
Statistic.scan(key, time);
```

## Wallet

```
Wallet.init(publicKey, privateKey);
Wallet.managed(account, secret, unsafe, create);
Wallet.sign(account, secret, transaction);
```

# Modules

Modules are wrapper methods that submit transactions to the network endpoint. Fees for methods are fixed and most recent fees can be found on [docs.xetareality.com](https://docs.xetareality.com).


## Transfer

```
Transfer.create(to, token, amount, from, message, transaction, submit);
```

## Token

```
Token.create(name, symbol, supply, reserve, whole, description, links, meta, preview, owner, frozen, category, object, mime, content, transaction, submit);
Token.update(token, name, description, links, meta, preview, frozen, category, mime, transaction, submit);
Token.mint(token, amount, transaction, submit);
```

## Pool

For pool creation, it is recommended to use the program-specific methods (which are wrappers around this method). Available pool programs are auction, launch, lock, loot, lottery, royalty, staking, swap, vote.

```
Pool.create(token, program, name, description, mechanism, candidates, rate, percentage, number, expires, answers, meta, minAmount, maxAmount, minTime, maxTime, transfersLimit, claimsLimit, tokenLimit, xetaLimit, tokenTarget, xetaTarget, transaction, submit);
```

## Claim
```
Claim.create(owner, token, tokenAmount, xetaAmount, expires, unlocks, frozen, category, meta, answer, number, transaction, submit);
Claim.update(claim, tokenAmount, xetaAmount, expires, unlocks, frozen, category, meta, answer, number, transaction, submit);
Claim.transfer(claim, to, transaction, submit);
Claim.resolve(claim, transaction, submit);
```

## Profile

```
Profile.update(name, description, links, meta, preview, category, transaction, submit);
```

## Allowance

```
Allowance.update(spender, token, amount, transaction, submit);
```

# Programs

Pools are based on programs, which are pre-written smart contracts on Xeta. For further details on individual functionalities or requirements check out the [Xeta Reality Docs](https://docs.xetareality.com). To get the pool object from pool-address, use the Pool.instance interface method.

## Auction

```
// Creator methods:
auction.deposit(unlocks, transaction, submit);
auction.close(transaction, submit);

// Participant methods:
auction.transfer(amount, transaction, submit);
auction.resolve(transaction, submit);
auction.cancel(transaction, submit);
```

## Launch

```
// Creator methods:
launch.deposit(amount, unlocks, transaction, submit);
launch.withdraw(claim, transaction, submit);
launch.close(transaction, submit);

// Participant methods:
launch.resolve(transaction, submit);
launch.transfer(amount, transaction, submit);
launch.claim(claim, transaction, submit);
```

## Lending

```
// Creator methods:
lending.deposit(amount, unlocks, transaction, submit);
lending.withdraw(claim, percentage, transaction, submit);

// Participant methods:
lending.liquidate(claim, transaction, submit);
lending.transfer(amount, collateralization, transaction, submit);
lending.settle(claim, transaction, submit);
```

## Lock

```
// Participant methods:
lock.transfer(amount, unlocks, expires, address, transaction, submit);
lock.claim(claim, transaction, submit);
```

## Loot

```
// Creator methods:
loot.deposit(token, unlocks, transaction, submit);
loot.withdraw(claim, transaction, submit);
loot.clear(transaction, submit);

// Participant methods:
loot.transfer(transaction, submit);
```

## Lottery

```
// Creator methods:
lottery.deposit(amount, unlocks, transaction, submit);
lottery.withdraw(claim, transaction, submit);
lottery.close(transaction, submit);
lottery.clear(transaction, submit);

// Participant methods:
lottery.transfer(transaction, submit);
lottery.claim(claim, transaction, submit);
lottery.resolve(transaction, submit);
```

## Royalty

```
// Creator methods:
royalty.deposit(amount, unlocks, transaction, submit);
royalty.withdraw(claim, transaction, submit);
royalty.close(transaction, submit);

// Participant methods:
royalty.transfer(token, transaction, submit);
royalty.claim(token, transaction, submit);
```

## Staking

```
// Creator methods:
staking.deposit(amount, unlocks, transaction, submit);
staking.withdraw(claim, percentage, transaction, submit);

// Participate methods:
staking.transfer(amount, unlocks, transaction, submit);
staking.claim(claim, transaction, submit);
```

## Swap

Swap pools are automatically created for all fungible tokens, with the same pool-address as the token-address.

```
// Liquidity provider methods:
swap.deposit(amount, xetaAmount, unlocks, transaction, submit);
swap.withdraw(claim, percentage, transaction, submit);

// Participant methods:
swap.transfer(token, amount, transaction, submit);
```

## Vote

```
// Creator methods:
vote.oracle(answer, transaction, submit);

// Participant methods:
vote.transfer(amount, answer, number, transaction, submit);
vote.resolve(transaction, submit);
vote.claim(claim, transaction, submit);
```

# Feedback & Contributions

We encourage contributions to this library. Please also join our social channels in case you have suggestions or require technical help.

[**Website**](https://xetareality.com)
[**App**](https://xeta.network)
[**Twitter**](https://twitter.com/XetaReality)
[**Telegram**](https://t.me/XetaReality)