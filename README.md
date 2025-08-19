# 🎮 UNO Game Implementation


## 📖 About

This is a Java implementation of the classic UNO card game, developed as part of the Advanced Programming course at AUT under the supervision of [Prof. Hossein Zeinali](https://scholar.google.com/citations?user=KaGpFx8AAAAJ&hl=en) during Spring 2020.

The project faithfully implements the official UNO rules with all special cards and gameplay mechanics, including:
- 🎨 Color and number matching
- ✨ Special action cards (Skip, Reverse, Draw Two)
- 🌈 Wild cards (Wild Color, Wild Draw Four)
- 📊 Score calculation and winner determination

## ✨ Features

### 🎲 Game Mechanics
- 👥 Supports 3-5 players (mix of human and AI opponents)
- 🃏 Complete card deck implementation (108 cards)
- 🔄 Turn management with clockwise/anticlockwise direction
- ⚡ Proper handling of all special card effects
- ⚠️ Penalty system for players who can't play a card
- 🎴 Stackable Draw cards (Draw Two + Draw Two = Draw Four)

### 💻 Technical Implementation
- 💻 Console-based interface with clear game state display
- 🔀 Random card distribution and shuffling
- 🤖 AI opponents with basic decision-making
- 📈 Score calculation based on official UNO rules
- ❌ Input validation and error handling

## 🎯 How to Play

### 🏆 Objective
Be the first player to get rid of all your cards and accumulate the lowest score over multiple rounds.

### 🃏 Card Types
- **🔢 Number cards (0-9)**: Match by color or number
- **🎭 Action cards**:
  - ⏭️ Skip: Next player loses turn
  - 🔄 Reverse: Changes game direction
  - ➕2️⃣ Draw Two: Next player draws 2 cards
- **🌌 Wild cards**:
  - 🎨 Wild Color: Choose next color
  - ➕4️⃣ Wild Draw Four: Choose color and next player draws 4 cards

### 📊 Scoring
- 🔢 Number cards: Face value
- 🎭 Action cards: 20 points each
- 🌈 Wild cards: 50 points each

## 🚀 Getting Started

### 📋 Prerequisites
- ☕ Java JDK 8 or later

### ⚙️ Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/Amirbehnam1009/UNO.git
   
2. Navigate to the project directory:
   ```bash 
   cd UNO

## ▶️ Running the Game
```bash
javac UnoGame.java
java UnoGame
```

### 🎮 Game Modes

1. **👤 Single Player**: Play against computer-controlled opponents
2. **👥 Multiplayer**: Play with friends on the same console

### 🎯 Game Rules

#### 🃏 Valid Plays
- Match color 🎨 of top discard card
- Match number 🔢 or symbol
- Play special card ✨ (if conditions met)
- Play wild card 🌈 (anytime)

#### ⚠️ Special Rules
- Draw cards stack: Draw Two + Draw Two = Draw Four penalty
- Wild Draw Four can only be played when no other valid moves
- Players must draw a card if they can't play
- Shout "UNO!" when down to your last card!

#### 📊 Scoring
- 🔢 Number cards: Face value (0-9 points)
- ✨ Action cards: 20 points each
- 🌈 Wild cards: 50 points each
- 🏆 Lowest total score after someone wins the round

### 🔮 Future Improvements

- [ ] 🖥️ Graphical User Interface (GUI) implementation
- [ ] 🧠 Enhanced AI with difficulty levels (easy/medium/hard)
- [ ] 🌐 Online multiplayer support
- [ ] 📱 Mobile application (Android/iOS)
- [ ] 🏆 Tournament mode with persistent scores
- [ ] 🌈 Improved console color rendering

### 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the project
2. Create your feature branch:
   ```bash
   git checkout -b feature/AmazingFeature
