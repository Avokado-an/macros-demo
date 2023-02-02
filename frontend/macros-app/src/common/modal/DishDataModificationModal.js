import React, { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';

export const DishDataModification = ({ dish, buttonName, modalHeader }) => {
    const [isOpen, setOpen] = useState(false);

    const toggleModal = () => {
        setOpen(!isOpen);
    }

    const onSave = () => {
        setOpen(!isOpen);
        //send request to server
    }

    return (
        <div className="modal show" style={{ display: 'block', position: 'initial' }}>

            <Button className="margin-bot-20 m-top-20 width-90" onClick={toggleModal} variant="dark">{buttonName}</Button>
            <Modal show={isOpen}>
                <Modal.Header>
                    <Modal.Title>{modalHeader}</Modal.Title>
                </Modal.Header>

                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Name Of The Dish</Form.Label>
                            <Form.Control
                                defaultValue={dish? dish.name: ""}
                                type="text"
                                placeholder="Baked Salmon"
                                autoFocus
                            />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                            <Form.Label>Cooking Method</Form.Label>
                            <Form.Select>
                                <option>Fried</option>
                                <option>Boiled</option>
                                <option>Roasted</option>
                            </Form.Select>
                        </Form.Group>
                    </Form>
                </Modal.Body>

                <Modal.Footer>
                    <Button variant="secondary" onClick={toggleModal}>Close</Button>
                    <Button variant="dark" onClick={onSave}>Save changes</Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
}