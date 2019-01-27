import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';
import Choose from './Choose';
import Battle from './Battle';
import Buy from './BuyMenu';
import Roam from './Roam';
import Shop from './Shop';
import BuyMenu from './BuyMenu';
import SellMenu from './SellMenu';
import Bag from './Bag';
import FightMenu from './FightMenu';
import PokemonParty from './PokemonParty';




class App extends Component {
  constructor(props) {
    super(props);
    this.setState = this.setState.bind(this);
    this.checkParty = this.checkParty.bind(this);
    this.shopToggle = this.shopToggle.bind(this);
    this.battleToggle = this.battleToggle.bind(this);
    this.bagToggle = this.bagToggle.bind(this);
    this.pokemonpartyToggle = this.pokemonpartyToggle.bind(this);
    this.roamToggle = this.roamToggle.bind(this);
    this.fightMenuToggle= this.fightMenuToggle.bind(this);
    this.buyMenuToggle = this.buyMenuToggle.bind(this);
    this.sellMenuToggle = this.sellMenuToggle.bind(this);
    this.roamToggle = this.roamToggle.bind(this);


  this.state={
    epokemon:'',
    choose:false,
    shop:false,
    bag:false,
    pokemonParty:false,
    roam:false,
    battle:false,
    buyMenu:false,
    sellMenu:false,
    fightMenu:false,
    roam:false,
     party: [],
  };}
  



componentWillMount() {
this.checkParty()
this.roamToggle()
}
 
checkParty(){
  fetch('api/player/show-party',{method: 'GET'})
.then(response => response.json())
.then(data=>this.setState({party:data}))

}
  shopToggle() {
    this.setState(prevState => ({
      shop: !prevState.shop
    }));
  }
  chooseToggle() {
    this.setState(prevState => ({
      choose: !prevState.choose
    }));
  }
  roamToggle() {
    this.setState(prevState => ({
      roam: !prevState.roam
    }));
  }
  battleToggle() {
    this.setState(prevState => ({
      battle: !prevState.battle
    }));
  }
  bagToggle() {
    this.setState(prevState => ({
      bag: !prevState.bag
    }));
  }

  pokemonpartyToggle() {
    this.setState(prevState => ({
      pokemonParty: !prevState.pokemonParty
    }));this.roamToggle()
  }
  buyMenuToggle() {
    this.setState(prevState => ({
      buyMenu: !prevState.buyMenu
    }));this.shopToggle()
  }
  sellMenuToggle() {
    this.setState(prevState => ({
      sellMenu: !prevState.sellMenu
    }));this.shopToggle()
  }
  fightMenuToggle() {
    this.setState(prevState => ({
      fightMenu: !prevState.fightMenu
    }));
  }
  roamToggle() {
    this.setState(prevState => ({
      roam: !prevState.roam
    }));
  }
   

  render() {
    const bag=this.state.bag;
    const pokemonParty=this.state.pokemonParty;
    const shop=this.state.shop;
    const party=this.state.party;
    const epokemon=this.state.epokemon
    const buyMenu=this.state.buyMenu;
    const sellMenu=this.state.sellMenu;
    const fightMenu=this.state.fightMenu;
    const choose=this.state.choose;
    const roam=this.state.roam;


   

  if(party==''){
    return (
      <div className="App">
      <Choose check={this.checkParty}/>
      </div>

    );}
     if(epokemon!=''){
      return (
        <div className="App"> 
        <Battle pokemonpartyToggle={this.pokemonpartyToggle} fightMenuToggle={this.fightMenuToggle} bagToggle={this.bagToggle}/> 
         </div>)
   }
    
   if(bag){
    return (
      <div className="App"> 
      <Bag bagToggle={this.bagToggle}/> 
       </div>)
  }
    if(pokemonParty){
    return (
      <div className="App"> 
      <PokemonParty pokemonpartyToggle={this.pokemonpartyToggle}/> 
       </div>)
  }
   if(shop){
    return (
      <div className="App"> 
      <Shop shopToggle={this.shopToggle} buyMenuToggle={this.buyMenuToggle} sellMenuToggle={this.sellMenuToggle}/> 
       </div>)
  }
   if(buyMenu){
    return (
      <div className="App"> 
      <BuyMenu buyMenuToggle={this.buyMenuToggle}/> 
       </div>)
  }
 
  
   if(sellMenu){
    return (
      <div className="App"> 
      <SellMenu sellMenuToggle={this.sellMenuToggle}/> 
       </div>)
  }
  
      if(fightMenu){
    return (
      <div className="App"> 
      <FightMenu fightMenuToggle={this.fightMenuToggle}/> 
       </div>)
  }
   if(roam){
     
    return (
      <div className="App">
      <Roam pokemonpartyToggle={this.pokemonpartyToggle} shopToggle={this.shopToggle} bagToggle={this.bagToggle}/>
      </div>


    ); }

  }
}

export default App;
