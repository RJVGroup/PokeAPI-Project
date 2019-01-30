import React, { Component } from 'react';
import './App.css'
import {Navbar, NavbarBrand, NavItem, NavLink, Nav,Container, Input, Table, Button, ButtonGroup} from 'reactstrap';



export default class Bag extends Component {
    constructor(props) {
      super(props);
      this.state = {
        bag: [],
        cPokemonIndex:this.props.cPokemonIndex
    }
}


componentDidMount() {
    fetch('api/player/show-bag',{method: 'GET'})
    .then(response => response.json())
    .then(data=>this.setState({bag:data}))
}

generatelist =()=>{
     let pos = 0;
     let bag=this.state.bag;
    var test = []       
    bag.forEach(function(arrayItem,arrayIndex,array){
        pos=pos++;
        test.push(
            <tr>
            <td>{array[arrayIndex].itemName}</td>
            <td>{array[arrayIndex].itemDescription}</td>
            </tr>
                
            
       )})
    return test;
}  


render() {
    return (
            <div className='col-game'>  
     

     <Container className="menu main-game-panel">
     
     <Table responsive>   
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                        </tr>
                    </thead>
                    <tbody>
                    {this.generatelist()}
                    </tbody>
                </Table>
                <br/>
                <button  onClick={this.props.close}>Go Back</button> 

    </Container> 
    </div>  
    )            
    }
}
