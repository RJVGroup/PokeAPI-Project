import React, { Component } from 'react';
import './App.css'
import { Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button, FormGroup } from 'reactstrap';
import Choose from './Choose';
import Battle from './Battle';
import Buy from './BuyMenu';
import Roam from './Roam';
import Shop from './Shop';
import PokemonParty from './PokemonParty';
import MainGame from './MainGame';





class App extends Component {
  constructor(props) {
    super(props);
    this.chosenClick = this.chosenClick.bind(this);
    this.changePokemon=this.changePokemon.bind(this);
    this.checkStarter=this.checkStarter.bind(this);


    this.state = {
      chosen: false,
      party: [],
      cpokemon: '',
      cPokemonIndex: '',
      name:'',
      id:'',
      level:'',
      locationtext:'You set off to find pokémon and adventure!',
location:'',
prevLocation:''
    };
  }




  componentWillMount() {
    this.checkStarter()
    }
  
  chosenClick=()=>{
    this.setState({ chosen: true })
  }

  checkStarter(){
    fetch('api/player/show-party',{method: 'GET'})
    .then(response => response.json())
    .then(data=>this.setState({party: data}))
  }
 
  changePokemon(pokemonIndex){
    fetch('api/player/show-pokemon/'+ pokemonIndex,{method: 'GET'})
    .then(response => response.json())
    .then(data=>this.setState({cpokemon:data, cPokemonIndex:pokemonIndex}));
   }
  render() {
    const chosen = this.state.chosen;
    const party = this.state.party;
    const cpokemon = this.state.cpokemon;
    const cPokemonIndex = this.state.cPokemonIndex;





    if (chosen == false && party == '') {
      return (
        <div className="App">
          <Choose close={this.chosenClick} change={this.changePokemon}/>
        </div>

      );
    }
   else return (
        <div className="App">
          <MainGame  cPokemonIndex={cPokemonIndex} cpokemon={cpokemon} change={this.changePokemon}/>
        </div>
      ); 
  }
}

export default App;
