import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Col, Row, Container, Input, TabContent, TabPane, Nav, Button,FormGroup} from 'reactstrap';
import Choose from './Choose';
import Battle from './Battle';
import Buy from './BuyMenu';
import Roam from './Roam';
import Shop from './Shop';




class App extends Component {
  constructor(props) {
    super(props);
    this.chosenClick=this.chosenClick.bind(this);
  this.state={
    chosen:false,
     party: [],
  };}
  



componentWillMount() {
fetch('api/player/show-party',{method: 'GET'})
.then(response => response.json())
.then(data=>this.setState({party:data}))
}
 chosenClick(){
    this.setState({chosen:true})
   }
   

  render() {
    const chosen=this.state.chosen;
    const party=this.state.party;

    if(chosen==false&&party==''){
    return (
      <div className="App">
      <Choose close={this.chosenClick}/>
      </div>

    );} else{
      return (
        <div className="App">
        <Roam/>
        </div>
  
      );}
  }
}

export default App;
