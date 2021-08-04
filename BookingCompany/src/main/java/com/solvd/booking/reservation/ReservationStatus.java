package com.solvd.booking.reservation;

import org.apache.log4j.Logger;

public enum ReservationStatus {
    APPROVED {
        @Override
        public boolean isPending(){
            return false;
        }

        @Override
        public void retry(Reservation<?> reservation) {
            LOGGER.debug("Reservation is already approved!");
        }
    },

    CANCELED {
        @Override
        public boolean isPending(){
            return false;
        }

        @Override
        public void retry(Reservation<?> reservation) {
            LOGGER.debug("Reservation is already canceled!");
        }
    },

    PENDING_APPROVAL {
        @Override
        public boolean isPending(){
            return true;
        }

        @Override
        public void retry(Reservation<?> reservation) {
            LOGGER.debug("Reservation is still being processed, retrying reservation...");

            reservation.reserve();
        }
    },

    PENDING_CANCELLATION {
        @Override
        public boolean isPending(){
            return true;
        }

        @Override
        public void retry(Reservation<?> reservation) {
            LOGGER.debug("Reservation is still being processed, retrying cancellation...");

            reservation.cancel();
        }
    };


    private final static Logger LOGGER = Logger.getLogger(ReservationStatus.class);


    public abstract boolean isPending();
    public abstract void retry(Reservation<?> reservation);
}
