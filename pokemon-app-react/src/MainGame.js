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
    this.move=this.move.bind(this);
    this.endBattle=this.endBattle.bind(this);
    this.run=this.run.bind(this);
    this.fightTurn=this.fightTurn.bind(this);
    this.pokemonStatus=this.pokemonStatus.bind(this);
    this.epokemonStatus=this.epokemonStatus.bind(this);

    this.state = {
      cpokemon:this.props.cpokemon,
      cPokemonIndex:this.props.cPokemonIndex,
      epokemon: null,
      disabled:false,
      battlestatus:0,
      name: this.props.name,
      id: this.props.id,
      level: this.props.level,
clicks:0,

      locationtext:'You set off to find pokémon and adventure!',
      location:'',
    };
  }




  componentDidMount() {
this.props.setStarter()
}
   
    
    componentDidUpdate(prevProps, prevState) {
      if (this.props.cpokemon !== prevProps.cpokemon) {
        this.setState({cpokemon:this.props.cpokemon, cPokemonIndex:this.props.cPokemonIndex,
          name:this.props.cpokemon.name,id:this.props.cpokemon.id,level:this.props.cpokemon.level});
      }
      if (this.state.location !== prevState.location) {
        this.setState({locationtext:'You arrived at a '+this.state.location+'.'});
      }
      if (this.state.epokemon !== prevState.epokemon) {
        this.setState({epokemon:this.state.epokemon});
      }
    }
  
  chosenClick() {
    this.setState({ chosen: true })
  }

   move(){
    
    this.setState({disabled:true});
    fetch('api/terrain/generate/5', { method: 'GET' })
    .then(response => response.json())
    .then(data => this.setState({ epokemon:data.pokemonEncountered,
    location:data.name, locationtext:'You arrived at a '+data.name+' again.',disabled:false
    }));
  }

  pokemonStatus(pokemonIndex){
    fetch('api/battle/getFriendlyStatus/'+ pokemonIndex, {method: 'GET'})
    .then(response => response.json())
    .then(data=>this.setState({cpokemon:data}));
   }
   
   epokemonStatus(){
    fetch('api/battle/getEnemyStatus/',{method: 'GET'})
    .then(response => response.json())
    .then(data=>this.setState({epokemon:data.enemyMon}));
   }
  fightTurn(pokemonIndex, moveIndex){
    fetch('api/battle/turnM/'+ pokemonIndex+'/'+ moveIndex,{method: 'POST'})
    .then(response => response.json())
    .then(data=>this.setState({battlestatus:data}));
   }
  endBattle(){
    if(this.state.battleStatus==(1||2||3)){
    this.setState({epokemon:null})}
   }
 
   run(){
    this.setState({epokemon:null})
   }
 
   
  render() {
    const cpokemon = this.state.cpokemon;
    const epokemon = this.state.epokemon;
    const locationtext = this.state.locationtext;
    const cPokemonIndex = this.state.cPokemonIndex;


if (epokemon == null) {
      
    return (
        <div className="App">
          <Roam disabled={this.state.disabled} change={this.props.change} cPokemonIndex={cPokemonIndex} name={this.state.name} id={this.state.id} level={this.state.level} move={this.move} location={locationtext} cpokemon={cpokemon} />
        </div>
      ); 
    }
      else{
        return (
        <div className='App'>
          <Battle  endBattle={this.endBattle} fightTurn={this.fightTurn} epokemonStatus={this.epokemonStatus} pokemonStatus={this.pokemonStatus} change={this.props.change} location={locationtext} epokemon={epokemon} cPokemonIndex={cPokemonIndex} cpokemon={cpokemon} close={this.run} />
        </div>)
  }
}
}

export default MainGame;
