import React, { Component } from 'react';
import './App.css'
import {ButtonGroup,Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';
import Bag from './Bag';
import PokemonParty from './PokemonParty';
import Shop from './Shop';
import Battle from './Battle';
import Choose from './Choose';
import FrontImg from './FrontImg';





export default class Roam extends Component {
    constructor(props) {
      super(props);
      this.bagToggle=this.bagToggle.bind(this);
      this.pokemonToggle=this.pokemonToggle.bind(this);
      this.shopToggle=this.shopToggle.bind(this);
   
     

    this.state = {
      party:'',
      cpokemon:this.props.cpokemon,
      cPokemonIndex:this.props.cPokemonIndex,
      bag:false,
      pokemon:false,
      shop:false,
      name:this.props.name,
      id: this.props.id,
      level: this.props.level,
    };
  }
 
  componentDidUpdate(prevProps) {
    if (this.props.cpokemon !== prevProps.cpokemon) {
      this.setState({cpokemon:this.props.cpokemon, name:this.props.cpokemon.name,id:this.props.cpokemon.id,level:this.props.cpokemon.level});
    }
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
  shopToggle() {
    this.setState(prevState => ({
      shop: !prevState.shop
    }));
  }
   
   chosenClick(){
    this.setState({chosen:true})
   }
    render() {
      const bag=this.state.bag;
      const pokemon=this.state.pokemon;
      const shop=this.state.shop;
      const locationtext=this.props.location;

      
    if(bag){
      return (
        <div className='col-game'> 
        <Bag close={this.bagToggle} cPokemonIndex={this.state.cPokemonIndex}/> 
         </div>)
    }
     if(pokemon){
      return (
        <div className='col-game'> 
        <PokemonParty change={this.props.change}close={this.pokemonToggle}/> 
         </div>)
    }
    if(shop){
      return (
        <div className='col-game'> 
        <Shop close={this.shopToggle}/> 
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
             <Container className="menu img-game-panel">
             Current Pokémon:
             <FrontImg id={this.state.id}/>
             {this.state.name} lvl:{this.state.level}
             </Container>
             <Container className="menu main-game-panel" >
            <Button  color="primary"className={" main-game-panel"} onClick={(e) => this.props.move(e)} disabled={this.props.disabled}>Move</Button>           
             <Button color="secondary" className=" main-game-panel" onClick={this.pokemonToggle}>Pokemon</Button>
             <Button color="success"  className=" main-game-panel" onClick={this.bagToggle}>Bag</Button>             
             <Button color="danger" className=" main-game-panel"  onClick={this.shopToggle}>Go to Shop</Button>
             </Container > </div>
        </div>  
      );
     
   }
  }