import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';
import Bag from './Bag';
import PokemonParty from './PokemonParty';
import Shop from './Shop';
import Battle from './Battle';
import Choose from './Choose';
import FrontImg from './FrontImg';





export default class Roam extends Component {
    constructor(props) {
      super(props);
      this.bagClick=this.bagClick.bind(this);
      this.bagClose=this.bagClose.bind(this);
      this.pokemonClick=this.pokemonClick.bind(this);
      this.pokemonClose=this.pokemonClose.bind(this);
      this.shopClick=this.shopClick.bind(this);
      this.shopClose=this.shopClose.bind(this);
      this.move=this.move.bind(this);
      this.run=this.run.bind(this);

    this.state = {
      party:'',
      cpokemon:'',
      epokemon:'',
      move:'',
      bag:false,
      pokemon:false,
      shop:false,
      locationtext:'You set off to find pokémon and adventure!'
    };
  }
  
  move() {
    Promise.all([fetch('api/player/move',{method: 'POST'}), fetch('api/player/show-party',{method: 'GET'})])
  .then(([res1, res2]) => Promise.all([res1.json(), res2.json()]))
  .then(([data1, data2]) => this.setState({
    move:data1,
    locationtext:'You arrived at a '+data1.name+'.',
    cpokemon:data2[0],
    }));

      if(this.state.move.pokemonEncountered==null){this.setState({epokemon:''})}else{this.setState({epokemon:this.state.move.pokemonEncountered})}
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
   shopClick(){
    this.setState({shop:true})
   }
   shopClose(){
    this.setState({shop:false})
   }
   run(){
    this.setState({epokemon:''})
   }
   chosenClick(){
    this.setState({chosen:true})
   }
    render() {
      const bag=this.state.bag;
      const pokemon=this.state.pokemon;
      const shop=this.state.shop;
      const locationtext=this.state.locationtext;
      const epokemon=this.state.epokemon;
      const cpokemon=this.state.cpokemon;

  
     
     if(epokemon!=''){
        return (
          <div className='col-game'> 
          <Battle location={locationtext} epokemon={epokemon} cpokemon={cpokemon} close={this.run}/> 
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
    if(shop){
      return (
        <div className='col-game'> 
        <Shop close={this.shopClose}/> 
         </div>)
    }

      return (
      
  
        <div className='col-game'>  
         <Container className="menu row-game">
        <h1 className=" main-game-panel">{locationtext}</h1>
         </Container> 
             <div className='row-game'>
             <Container className=" menu img-game-panel">      
             <div className="pokemonimg " >
        <img src={"https://66.media.tumblr.com/088786d466c3a315d6043b8e59d96770/tumblr_msu2ojWkqz1scncwdo1_500.gif"} /></div>
             </Container> 
             
             <Container className="menu main-game-panel" >
            <button className=" main-game-panel" onClick={this.move}>Move</button>           
             <button className=" main-game-panel" onClick={this.pokemonClick}>Pokemon</button>
             <button className=" main-game-panel" onClick={this.bagClick}>Bag</button>             
             <button className=" main-game-panel"  onClick={this.shopClick}>Go to Shop</button>

             </Container > </div>
        </div>  
      );
     
   }
  }