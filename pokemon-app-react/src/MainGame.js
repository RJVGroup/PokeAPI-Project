import React, { Component } from 'react';
import './App.css'
import { Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button, FormGroup } from 'reactstrap';
import Choose from './Choose';
import Battle from './Battle';
import Buy from './BuyMenu';
import Roam from './Roam';
import Shop from './Shop';
import PokemonParty from './PokemonParty';





class MainGame extends Component {
  constructor(props) {
    super(props);
    this.chosenClick = this.chosenClick.bind(this);
    this.move = this.move.bind(this);
    this.endBattle = this.endBattle.bind(this);
    this.pokemonStatus = this.pokemonStatus.bind(this);
    this.epokemonStatus = this.epokemonStatus.bind(this);
    this.generateRoamStatus = this.generateRoamStatus.bind(this);
    this.winCounter = this.winCounter.bind(this);
    this.loseCounter = this.loseCounter.bind(this);
    this.state = {
      cpokemon: this.props.cpokemon,
      cPokemonIndex: this.props.cPokemonIndex,
      epokemon: null,
      disabled: false,
      locationtext: 'You set off to find pokémon and adventure!',
      location: '',
      wins: 0,
      losses: 0
    };
  }




  componentWillMount() {
    this.props.change(0)

  }


  componentDidUpdate(prevProps, prevState) {
    if (this.props.cpokemon !== prevProps.cpokemon) {
      this.setState({
        cpokemon: this.props.cpokemon, cPokemonIndex: this.props.cPokemonIndex,
      });
    }
    if (this.state.location !== prevState.location) {
      this.setState({ locationtext: 'You arrived at a ' + this.state.location + '' });
    }

  }

  chosenClick() {
    this.setState({ chosen: true })
  }
  winCounter() {
    this.setState({ wins: this.state.wins + 1 })
  }
  loseCounter() {
    this.setState({ losses: this.state.losses + 1 })
  }
  move() {
    if (this.state.cpokemon.status != 'FAINT') {
      this.setState({ disabled: true, roamStatus: 1 });
      fetch('api/player/move', { method: 'POST' })
        .then(response => response.json())
        .then(data => this.setState({
          epokemon: data.pokemonEncountered,
          location: data.name, locationtext: 'You arrived at a ' + data.name + ' again', disabled: false
        }));
    }
  }
  generateRoamStatus = () => {
    if (this.state.cpokemon.status != 'FAINT') {
      return <div><h1 className=" main-game-panel">{this.state.locationtext}</h1></div>
    }
    else {
      return <div>
        <h1 className=" main-game-panel">Your {this.state.cpokemon.name} has fainted. Change your current pokémon or use a potion to heal</h1></div>
    }
  }

  pokemonStatus(pokemonIndex) {
    fetch('api/battle/getFriendlyStatus/' + pokemonIndex, { method: 'GET' })
      .then(response => response.json())
      .then(data => this.setState({ cpokemon: data }));
  }

  epokemonStatus() {
    fetch('api/battle/getEnemyStatus/', { method: 'GET' })
      .then(response => response.json())
      .then(data => this.setState({ epokemon: data }));
  }

  endBattle() {
    this.epokemonStatus();
        setTimeout(function () {
          this.setState({ epokemon: null })
        }.bind(this), 5000)
  }



  render() {
    const cpokemon = this.state.cpokemon;
    const epokemon = this.state.epokemon;
    const locationtext = this.state.locationtext;
    const cPokemonIndex = this.state.cPokemonIndex;


    if (epokemon == null) {

      return (
        <div className="App">
          <Roam losses={this.state.losses} wins={this.state.wins} generateRoamStatus={this.generateRoamStatus} disabled={this.state.disabled} change={this.props.change} cPokemonIndex={cPokemonIndex} move={this.move} location={locationtext} cpokemon={cpokemon} />
        </div>
      );
    }
    else {
      return (
        <div className='App'>
          <Battle loseCounter={this.loseCounter} winCounter={this.winCounter} endBattle={this.endBattle} epokemonStatus={this.epokemonStatus} pokemonStatus={this.pokemonStatus} change={this.props.change} location={locationtext} epokemon={epokemon} cPokemonIndex={cPokemonIndex} cpokemon={cpokemon} />
        </div>)
    }
  }
}

export default MainGame;
