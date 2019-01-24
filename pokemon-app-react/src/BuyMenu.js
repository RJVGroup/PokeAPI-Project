import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavItem, NavLink, Nav,Container, Input, Table, Button, ButtonGroup} from 'reactstrap';

export default class BuyMenu extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            shopstock: [],
            itemdescription:''
        }
    }


    componentDidMount() {
        fetch('api/shop/generate',{method: 'GET'})
        .then(response => response.json())
        .then(data=>this.setState({shopstock:data.shopInventory}))
    }
    generateshop =()=>{
         let pos = 0;
         let shopstock=this.state.shopstock;
        var test = []
       

        shopstock.forEach(function(arrayItem,arrayIndex,array){
            pos=pos++;
            test.push(
                <tr>

                <td>{array[arrayIndex].itemName}</td>
                <td>{array[arrayIndex].itemPrice}</td>
                <td>{array[arrayIndex].itemDescription}</td>
                <td><button >Buy</button></td>

                </tr>
                    
                
           )})
        return test;
    }  
    
    render() {
        return (
                <div className='col-game'>  
         

         <Container className="menu main-game-panel">
         <button  onClick={this.props.close}>Go Back</button>        
          <div>Wallet:?</div>

         <br/>
         <Table responsive>   
                        <thead>
                            <tr>
                                <th>Item Name</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                        {this.generateshop()}
                        </tbody>
                    </Table>   
        </Container> 
        </div>  
        )            
        }
}
