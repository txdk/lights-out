package com.txdk;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.txdk.repository.GameRepository;
import com.txdk.service.GameService;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {
    
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    private boolean[][] gameState;

    @Before
    public void setUp()
    {
        gameState = new boolean[][]{
            {false, false, false}, 
            {false, false, false},
            {false, false, false}
        };

        when(gameRepository.getGameState()).thenReturn(gameState);

        // Mock getStateAtCoords
        doAnswer(new Answer<Boolean>()
        {
            @Override public Boolean answer(final InvocationOnMock invocation) throws Throwable
            {
                int row = (Integer) invocation.getArguments()[0];
                int col = (Integer) invocation.getArguments()[1];
                return gameState[row][col];
            }
        }
        ).when(gameRepository).getStateAtCoords(Mockito.anyInt(), Mockito.anyInt());

        // Mock setStateAtCoords
        doAnswer(new Answer<Void>()
        {
            @Override public Void answer(final InvocationOnMock invocation) throws Throwable
            {
                int row = (Integer) invocation.getArguments()[1];
                int col = (Integer) invocation.getArguments()[2];
                gameState[row][col] = (boolean) invocation.getArguments()[0];
                return null;
            }
        }
        ).when(gameRepository).setStateAtCoords(Mockito.anyBoolean(), Mockito.anyInt(), Mockito.anyInt());
    }

    @Test
    public void toggleCellAtCoordsTest()
    {
        gameService.toggleCellAtCoords(1, 1);
        assertEquals(true, gameService.getGameState()[1][1]);

        gameService.toggleCellAtCoords(1, 1);
        assertEquals(false, gameService.getGameState()[1][1]);
    }

    @Test
    public void toggleNeighboursAtCoordsTest()
    {
        gameService.toggleNeighboursAtCoords(1, 1);
        assertEquals(true, gameService.getGameState()[0][1]);
        assertEquals(true, gameService.getGameState()[2][1]);
        assertEquals(true, gameService.getGameState()[1][0]);
        assertEquals(true, gameService.getGameState()[1][2]);
        assertEquals(false, gameService.getGameState()[1][1]);
        assertEquals(false, gameService.getGameState()[2][2]);
    }

    @Test
    public void getRowFromCellIndexTest()
    {
        assertEquals(0, gameService.getRowFromCellIndex(0));
        assertEquals(0, gameService.getRowFromCellIndex(2));
        assertEquals(1, gameService.getRowFromCellIndex(4));
        assertEquals(1, gameService.getRowFromCellIndex(5));
        assertEquals(2, gameService.getRowFromCellIndex(8));
    }

    @Test
    public void getColumnFromCellIndexTest()
    {
        assertEquals(0, gameService.getColumnFromCellIndex(0));
        assertEquals(2, gameService.getColumnFromCellIndex(2));
        assertEquals(1, gameService.getColumnFromCellIndex(4));
        assertEquals(2, gameService.getColumnFromCellIndex(5));
        assertEquals(2, gameService.getColumnFromCellIndex(8));
    }
}
