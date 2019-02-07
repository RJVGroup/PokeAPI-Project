import React, { Component } from 'react';
import './App.css'
import { Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button, FormGroup } from 'reactstrap';
import Bag from './Bag';
import PokemonParty from './PokemonParty';
import FightMenu from './FightMenu';
import FrontImg from './FrontImg';
import BackImg from './BackImg';



export default class Battle extends Component {
  constructor(props) {
    super(props);
    this.bagToggle = this.bagToggle.bind(this);
    this.pokemonToggle = this.pokemonToggle.bind(this);
    this.fightMenuToggle = this.fightMenuToggle.bind(this);
    this.fightTurn = this.fightTurn.bind(this);
    this.generateBattleStatus = this.generateBattleStatus.bind(this);
    this.getEnemyMove = this.getEnemyMove.bind(this);
    this.run = this.run.bind(this);



    this.state = {
      epokemon: this.props.epokemon.enemyMon,
      cpokemon: this.props.cpokemon,
      cPokemonIndex: this.props.cPokemonIndex,
      battleStatus: '',
      enemyMove: '',
      fightMenu: false,
      bag: false,
      pokemon: false,
      disabled: false,
    };
  }
  componentWillMount() {
    if (this.state.epokemon.status == 'FAINT') {
      this.setState({ battleStatus: 5, disabled: true }); this.props.endBattle()
    }
  }
  componentDidUpdate(prevProps) {
    if (this.props.cpokemon !== prevProps.cpokemon) {
      this.setState({ cpokemon: this.props.cpokemon, cPokemonIndex: this.props.cPokemonIndex });
    }
    if (this.props.epokemon !== prevProps.epokemon) {
      this.setState({ epokemon: this.props.epokemon.enemyMon });
    }
  }
  fightMenuToggle() {
    this.setState(prevState => ({
      fightMenu: !prevState.fightMenu
    }));
  }
  bagToggle() {
    this.setState(prevState => ({
      bag: !prevState.bag
    }));
  }
  pokemonToggle() {
    this.setState(prevState => ({
      pokemon: !prevState.pokemon
    }));
  }

  fightTurn(pokemonIndex, moveIndex) {
    fetch('api/battle/turnM/' + pokemonIndex + '/' + moveIndex, { method: 'POST' })
      .then(response => response.json())
      .then(data => {
        if (data === 0) {
          this.setState({ battleStatus: data }); this.props.epokemonStatus(); this.getEnemyMove()
        }
        if (data === 1) {
          this.setState({ battleStatus: data, disabled: true }); this.props.winCounter(); this.props.endBattle()
        }
        if (data === 2) {
          this.setState({ battleStatus: data, disabled: true }); this.props.loseCounter(); this.props.endBattle()
        }
      })
  }
  getEnemyMove() {
    fetch('api/battle/enemyMostRecentMove', { method: 'GET' })
      .then(response => response.json())
      .then(data => this.setState({ enemyMove: data.moveName }))
  }

  generateBattleStatus = () => {
    if (this.state.battleStatus === 0) {
      return <div>
        <h1>The wild {this.state.epokemon.name} used {this.state.enemyMove}.</h1></div>
    }
    else if (this.state.battleStatus === 1) {
      return <div><h1>The wild {this.state.epokemon.name} has fainted! You win!</h1></div>

    }
    else if (this.state.battleStatus === 2) {
      return <div><h1>Your {this.state.cpokemon.name} has fainted! You are the worst pokémon trainer ever!</h1></div>
    }
    else if (this.state.battleStatus === 3) {
      return <div><h1>{this.state.cpokemon.name} got away successfully.</h1></div>
    }
    else if (this.state.battleStatus === 4) {
      return <div><h1>{this.state.cpokemon.name} tried to run away but failed.</h1>
        <h1>The wild {this.state.epokemon.name} used {this.state.enemyMove}.</h1></div>
    }
    else if (this.state.battleStatus === 5) {
      return <div><h1>This wild {this.state.epokemon.name} has been defeated already.</h1></div>
    }
    else {
      return <div><h2>{this.props.location} and a wild {this.state.epokemon.name} appeared!</h2>
        <h2>What will {this.state.cpokemon.name} do!</h2></div>
    }
  }

  run(pokemonIndex) {
    fetch('api/battle/turnR/' + pokemonIndex, { method: 'POST' })
      .then(response => response.json())
      .then(data => {
        if (data === 0) {
          this.setState({ battleStatus: 4 }); this.props.pokemonStatus(pokemonIndex); this.props.epokemonStatus(); this.getEnemyMove()
        }
        if (data === 3) {
          this.setState({ battleStatus: data, disabled: true }); this.props.loseCounter(); this.props.endBattle()
        }
      })
  }

  render() {
    const fightMenu = this.state.fightMenu;
    const epokemon = this.state.epokemon
    const cpokemon = this.state.cpokemon

    const bag = this.state.bag;
    const pokemon = this.state.pokemon;
    const cPokemonIndex = this.state.cPokemonIndex;


    if (fightMenu) {
      return (
        <div className='col-game'>
          <FightMenu endBattle={this.props.endBattle} fightTurn={this.fightTurn} epokemonStatus={this.props.epokemonStatus} pokemonStatus={this.props.pokemonStatus} cpokemon={cpokemon} cPokemonIndex={cPokemonIndex} close={this.fightMenuToggle} />
        </div>)
    }
    if (bag) {
      return (
        <div className='col-game'>
          <Bag close={this.bagToggle} cPokemonIndex={cPokemonIndex} />
        </div>)
    }
    if (pokemon) {
      return (
        <div className='col-game'>
          <PokemonParty change={this.props.change} close={this.pokemonToggle} />
        </div>)
    }

    return (

      <div className='col-game'>
        <Container className="menu row-game">
          <div className="main-game-panel"  >
            <BackImg id={cpokemon.id} />
            {cpokemon.name} Level:{cpokemon.level}
            <br />
            HP:{cpokemon.currentHP}/{cpokemon.hp}</div>
          <br />

          <div className="main-game-panel"  >
            <FrontImg id={epokemon.id} />
            {epokemon.name} Level:{epokemon.level}
            <br />
            HP:{epokemon.currentHP}/{epokemon.hp}</div>
        </Container>
        <div className='row-game'>
          <Container className="menu main-game-panel" >
            {this.generateBattleStatus()}
          </Container>
          <Container className="menu main-game-panel" >
            <Button color="primary" className=" main-game-panel" onClick={this.fightMenuToggle} disabled={this.state.disabled}>Fight</Button>
            <Button color="primary" className=" main-game-panel" onClick={this.pokemonToggle} disabled={this.state.disabled}>Pokemon</Button>
            <Button color="primary" className=" main-game-panel" onClick={this.bagToggle} disabled={this.state.disabled}>Bag</Button>
            <Button color="primary" className=" main-game-panel" onClick={(e) => this.run(cPokemonIndex, e)} disabled={this.state.disabled}>Run</Button>
          </Container> </div>
      </div>
    );
  }

}