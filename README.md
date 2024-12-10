# Blockchain_Model_Implementation_with_Dynamic_Mining_Java

## Overview

This project simulates a basic blockchain mining process, where multiple miners compete to find a valid hash for a new block. Each miner has a certain amount of hash power that affects their mining speed. The miner who computes the valid hash first wins the mining reward.

The blockchain uses Proof of Work (PoW) consensus to validate new blocks. The difficulty of the PoW is represented by the number of leading zeros required in the hash (adjusted by the `difficulty` parameter).

## Features

- **Miner Class**: Represents a miner with an ID, hash power, and cryptocurrency balance.
- **Mining Service**: The `MinerServiceImpl` class is responsible for the mining process and validation of mined blocks.
- **Blockchain**: A simple blockchain that stores the blocks.
- **Block**: A block that holds the transaction root, hash, nonce, and metadata about the block.
- **Multi-threading**: Miners are represented as separate threads that mine blocks concurrently, with their mining speed influenced by their hash power.

## How the Mining Process Works

1. **Proof of Work**: Miners must calculate a valid hash for the block by iterating through possible values of a nonce.
2. **Difficulty**: The block hash must start with a number of zeros corresponding to the `difficulty` level (e.g., difficulty level of 4 requires the hash to start with `0000`).
3. **Hash Power**: Each miner has a `hashPower` that affects how quickly they can compute hashes. Higher hash power means faster mining.
4. **Mining Race**: Miners race to find the valid hash. The first miner to compute a valid hash wins and adds the block to the blockchain.
5. **Concurrency**: The mining process is multi-threaded, with each miner running in its own thread. If one miner successfully mines a block, the others stop mining.

## Prerequisites

Before you can run this project, ensure you have the following:

- **Java 8 or later**.
- **Maven** (for project management and dependencies).
- **IDE** (e.g., IntelliJ IDEA, Eclipse) or a command line interface.

## Project Structure

The project is divided into several key components:

- `org.blockchain.models`: Contains the model classes for `Miner`, `Block`, and `Blockchain`.
- `org.blockchain.services.miner`: Contains the `MinerServiceImpl` class that handles the mining logic.
- `org.blockchain.services.block`: Contains the `BlockService` class responsible for calculating hashes.
- `org.blockchain.services.blockchain`: Contains the `BlockchainService` class for managing the blockchain and validating blocks.

## Dependencies

- **Java**: The project is built using Java.
- **Maven**: Maven is used to manage dependencies and build the project.
- **Concurrent Programming**: The project makes extensive use of threads for simulating multiple miners working in parallel.


### Clone the Repository

    ```bash
    git clone https://github.com/matoussi-roua/Blockchain_Model_Implementation_with_Dynamic_Mining_Java
    cd Blockchain_Model_Implementation_with_Dynamic_Mining_Java

## How the Mining Process Works in Detail
### Mining Loop:

Each miner starts mining by calculating hashes for the block and appending increasing values of the nonce.
The hash is calculated using the formula: hash = SHA-256(baseHash + nonce).
The miner's goal is to find a hash that starts with a string of zeros. The number of zeros is determined by the block's difficulty.
### Hash Power:

The miner's hashPower affects how fast they can mine. It essentially determines how many hash computations a miner can perform per unit of time.
The delay between iterations is adjusted by the formula: Thread.sleep(1000 / miner.getHashPower()). Miners with higher hash power will compute faster.
### Winner Determination:

The first miner to compute a valid hash (starting with the required number of zeros) wins the race and adds the block to the blockchain.
Other miners stop their mining once a valid block is found.
## Blockchain Update:

When a miner successfully mines a block, the blockchainService validates the block and adds it to the blockchain if valid.
The miner is rewarded with cryptocurrency, which increases their balance.
## Classes and Methods
### MinerServiceImpl Class
  Methods:
mineBlock(Miner miner, Block block, BlockChain blockchain, AtomicBoolean blockMined): Handles the mining process for a miner, including computing the hash, checking if it meets the difficulty, and updating the blockchain if valid.
### MinerThread Class
  Runnable Implementation:
Each miner runs in its own thread. The thread is responsible for calling the mining service and stopping once the block is successfully mined.
It takes in the miner object, the block to mine, the blockchain, and a shared AtomicBoolean to track whether the block has already been mined.
### Main Class
  Execution:
Initializes the blockchain and miners, and starts the mining process in multiple threads.
Outputs the results once the mining process is complete.
## Contribution
Contributions to this project are welcome. To contribute, follow these steps:

Fork the repository.
Create a new branch (git checkout -b feature-branch).
Make your changes and commit (git commit -am 'Add new feature').

# License
This project is licensed under the MIT License - see the LICENSE file for details.

# Acknowledgements
Thanks to the open-source community for their contributions to Java and blockchain technologies.
Special thanks to anyone who has provided feedback or suggestions for improving this project.

### Key Sections:
1. **Overview**: Explains the project and its core functionality.
2. **Features**: Lists the main features of the project, including multi-threaded mining and blockchain validation.
3. **How the Mining Process Works**: Describes the mining process in detail, including difficulty adjustment and hash power influence.
4. **Practical Instructions**: Explains how to clone, build, and run the project, including sample output.
5. **Code Explanation**: Provides an overview of the core classes and methods in the project.
6. **Contribution**: Information on how to contribute to the project.
7. **License**: Mentions the MIT License.

This `README.md` should give anyone using or contributing to the project a solid understanding of its structure and how it works.

