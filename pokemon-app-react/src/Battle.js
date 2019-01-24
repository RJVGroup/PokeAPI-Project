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
      this.fightClick=this.fightClick.bind(this);
      this.fightClose=this.fightClose.bind(this);
      this.bagClick=this.bagClick.bind(this);
      this.bagClose=this.bagClose.bind(this);
      this.pokemonClick=this.pokemonClick.bind(this);
      this.pokemonClose=this.pokemonClose.bind(this);

    this.state = {
      epokemon:[],
      pokemon:[],
      fight:false,
      bag:false,
      pokemon:false
    };
  }
  fightClick(){
    this.setState({fight:true})
   }
  fightClose(){
    this.setState({fight:false})
   }
  bagClick(){
    this.setState({bag:true})
   }
   bagClose(){
    this.setState({bag:false})
   }
   pokemonClick(){
    this.setState({pokemon:true})
   }
   pokemonClose(){
    this.setState({pokemon:false})
   }
   componentDidMount() {
    fetch('api/pokemon/'+this.props.elevel+'/'+this.props.ename,{method: 'GET'})
    .then(response => response.json())
    .then(data=>this.setState({epokemon:data}));
}
  
  
    render() {
      const fight=this.state.fight;

      const bag=this.state.bag;
      const pokemon=this.state.pokemon;
      
      if(fight){
        return (
          <div className='col-game'> 
          <FightMenu close={this.fightClose}/> 
           </div>)
      }
    if(bag){
      return (
        <div className='col-game'> 
        <Bag close={this.bagClose}/> 
         </div>)
    }
     if(pokemon){
      return (
        <div className='col-game'> 
        <PokemonParty close={this.pokemonClose}/> 
         </div>)
    }
    
        return(
  
        <div className='col-game'>  
         <Container className="menu row-game">
         <div className="pokemonimg main-game-panel"  >  
         <BackImg id='1'/>
         </div>
         <br/>

         <div className="pokemonimg main-game-panel"  >  
         <FrontImg id={this.state.epokemon.id}/>
         {this.state.epokemon.name} Level:{this.state.epokemon.level}
         <br/>
         HP:{this.state.epokemon.currentHP}</div>
         </Container> 
             <div className='row-game'>
             <Container className="menu main-game-panel" >      
             
             </Container> 
             <Container className="menu main-game-panel" >      
             <button className=" main-game-panel"  onClick={this.fightClick}>Fight</button>
             <button className=" main-game-panel" onClick={this.pokemonClick}>Pokemon</button>
             <button className=" main-game-panel" onClick={this.bagClick}>Bag</button>
             <button className=" main-game-panel"onClick={this.props.close}>Run</button>
             </Container> </div>
        </div>  
      );
     } 
    
  }