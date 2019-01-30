import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';
import Bag from './Bag';
import PokemonParty from './PokemonParty';
import FightMenu from './FightMenu';

import FrontImg from './FrontImg';
import BackImg from './BackImg';



export default class Battle extends Component {
    constructor(props) {
      super(props);
      this.bagToggle=this.bagToggle.bind(this);
      this.pokemonToggle=this.pokemonToggle.bind(this);
      this.fightMenuToggle=this.fightMenuToggle.bind(this);
    this.state = {
      epokemon:this.props.epokemon,
      cpokemon:this.props.cpokemon,
      cPokemonIndex:this.props.cPokemonIndex,

      fightMenu:false,
      bag:false,
      pokemon:false
    };
  }
  componentDidUpdate(prevProps) {
    if (this.props.cpokemon !== prevProps.cpokemon) {
      this.setState({cpokemon:this.props.cpokemon, cPokemonIndex:this.props.cPokemonIndex});
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
   
  
  
    render() {
      const fightMenu=this.state.fightMenu;
      const epokemon=this.state.epokemon
      const cpokemon=this.state.cpokemon

      const bag=this.state.bag;
      const pokemon=this.state.pokemon;
      const cPokemonIndex=this.state.cPokemonIndex;

      
    if(fightMenu){
      return (
        <div className='col-game'> 
        <FightMenu cpokemon={cpokemon} cPokemonIndex={cPokemonIndex} close={this.fightMenuToggle}/> 
        </div>)
    }
    if(bag){
      return (
        <div className='col-game'> 
        <Bag close={this.bagToggle} cPokemonIndex={cPokemonIndex}/> 
         </div>)
    }
     if(pokemon){
      return (
        <div className='col-game'> 
        <PokemonParty change={this.props.change} close={this.pokemonToggle}/> 
         </div>)
    }
    
        return(
  
        <div className='col-game'>  
         <Container className="menu row-game">
         <div className="main-game-panel"  >  
         <BackImg id={cpokemon.id}/>
         {cpokemon.name} Level:{cpokemon.level}
         <br/>
         HP:{cpokemon.currentHP}</div>        
         <br/>

         <div className="main-game-panel"  >  
         <FrontImg id={epokemon.id}/>
         {epokemon.name} Level:{epokemon.level}
         <br/>
         HP:{epokemon.currentHP}</div>
         </Container> 
             <div className='row-game'>
             <Container className="menu main-game-panel" > 
             <h1>{this.props.location}</h1>    
             <h1>A wild {epokemon.name} appeared!</h1>
             </Container> 
             <Container className="menu main-game-panel" >      
             <button className=" main-game-panel"  onClick={this.fightMenuToggle}>Fight</button>
             <button className=" main-game-panel" onClick={this.pokemonToggle}>Pokemon</button>
             <button className=" main-game-panel" onClick={this.bagToggle}>Bag</button>
             <button className=" main-game-panel"onClick={this.props.close}>Run</button>
             </Container> </div>
        </div>  
      );
     } 
    
  }